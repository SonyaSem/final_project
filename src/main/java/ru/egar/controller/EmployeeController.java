package ru.egar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egar.dto.EmployeeDTO;
import ru.egar.exception.PersonnelNumberAlreadyExistException;

import ru.egar.reporitory.DepartmentRepository;
import ru.egar.reporitory.PositionRepository;
import ru.egar.service.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DocumentService documentService;
    private final InfractionService infractionService;
    private final EducationService educationService;
    private final CertificationService certificationService;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    @GetMapping ("/")
    public String home(@RequestParam(name="number", required = false) Integer number,
                        @RequestParam(name="lastName", required = false) String lastName,
                        @RequestParam(name="firstName", required = false) String firstName,
                        @RequestParam(name="secondName", required = false) String secondName,
                        @RequestParam(name="phone", required = false) String phone,
                        @RequestParam(name="position", required = false) String position,
                        @RequestParam(name="department", required = false) String department,
                        @RequestParam(name="sort1", required = false) String sort1,
                        @RequestParam(name="sort2", required = false) String sort2,
                        @RequestParam(name="sort3", required = false) String sort3,
                       Model model) {

        if(lastName != null &&lastName.equals("")){
            lastName = null;
        }
        if(firstName != null && firstName.equals("")){
            firstName = null;
        }
        if(secondName != null && secondName.equals("")){
            secondName = null;
        }
        if(phone != null && phone.equals("")){
            phone = null;
        }

        if(position != null && position.equals("")){
            position = null;
        }

        if(department != null && department.equals("")){
            department = null;
        }

        if(sort1 != null || sort2 ==null || sort3 ==null){
            model.addAttribute("employees", employeeService.getListEmployee(number, lastName, firstName, secondName, phone));
        }
        if(sort2 != null){
            model.addAttribute("employees", employeeService.getListEmployee(position, department));
        }
        if(sort3 !=null){
            model.addAttribute("employees", employeeService.getEmployeesOnDismissal());
        }

        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("positions", positionRepository.findAll());
        return "employee";
    }

    @PostMapping("/employee/add")
    public String createDocument( @ModelAttribute("employee") @Valid EmployeeDTO employee, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("employee", employee);
            return "add_employee";
        }
        try {
            employeeService.saveEmployee(employee);
        }catch (PersonnelNumberAlreadyExistException e){
            model.addAttribute("err", e.getMessage());
            model.addAttribute("employee", employee);
            return "add_employee";
        }

        return "redirect:/";
    }

    @GetMapping ("/employee/add")
    public String addEmployee(Model model){

        model.addAttribute("employee", new EmployeeDTO());

        return "add_employee";
    }

    @GetMapping("/employee/{personnelNumber}")
    public String showEmployee(@PathVariable Integer  personnelNumber,
                               Model model){
        model.addAttribute("employee", employeeService.getPersonnelNumber(personnelNumber));
        model.addAttribute("certifications", certificationService.getEmployeeCertification(personnelNumber));
        model.addAttribute("infractions", infractionService.getListEmployeeInfraction(personnelNumber));
        model.addAttribute("educations",educationService.getListEmployeeEducation(personnelNumber));
        model.addAttribute("documents", documentService.getListEmployeeDocument(personnelNumber));
        return "employee_info";
    }

    @GetMapping("/statistic/certification/employee")
    public String showStatisticCertification(@RequestParam(name="year", required = false) Integer year, Model model){
        model.addAttribute("employees", employeeService.getCertificationStatistic(year));
        return "statistic/certification_employee";
    }

    @GetMapping("/statistic/infraction/employee")
    public String showStatisticInfraction(@RequestParam(name="year", required = false) Integer year, Model model){
        model.addAttribute("employees", employeeService.getInfractionStatistic(year));
        return "statistic/infraction_employee";
    }

   // @ExceptionHandler(PersonnelNumberAlreadyExistException.class)
  //  public ResponseEntity<String> handle(Exception ex) {
//}



}
