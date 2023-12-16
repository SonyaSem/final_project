package ru.egar.employee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egar.certification.service.CertificationService;
import ru.egar.document.service.DocumentService;
import ru.egar.employee.dto.EmployeeDTO;
import ru.egar.education.service.EducationService;
import ru.egar.employee.dto.EmployeeInfoDTO;
import ru.egar.employee.model.Employee;
import ru.egar.employee.service.EmployeeService;
import ru.egar.exception.PersonnelNumberAlreadyExistException;

import ru.egar.department.repo.DepartmentRepository;
import ru.egar.infraction.service.InfractionService;
import ru.egar.position.repo.PositionRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping ("/page/{page}")
    public String home(Model model, @PathVariable (name = "page", required = false) int currentPage,
                       @Param("field") String field, @Param("direction") String direction,
                       @RequestParam(name="string", required = false) String string,
                       @RequestParam(name="position", required = false) String position,
                       @RequestParam(name="department", required = false) String department,
                       @RequestParam(name="sort2", required = false) String sort2,
                       @RequestParam(name="sort3", required = false) String sort3) {

        Page<EmployeeInfoDTO> page;
        if(sort2 != null){
            page = employeeService.getListEmployee(position, department, field, direction, currentPage-1);
        }
        else if(sort3 !=null){
            page = employeeService.getEmployeesOnDismissal(field, direction, currentPage-1);
        }else {
            page = employeeService.getPageEmployee(string, field, direction, currentPage-1) ;
        }


        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("field", field);
        model.addAttribute("string", string);
        model.addAttribute("direction", direction);
        model.addAttribute("reverse", direction.equals("asc")? "desc":"asc");
        model.addAttribute("employees", page.getContent());


        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("positions", positionRepository.findAll());
        return "employee";
    }

    @GetMapping ("/")
    public String home(Model model,
                       @RequestParam(name="string", required = false) String string,
                       @RequestParam(name="position", required = false) String position,
                       @RequestParam(name="department", required = false) String department,
                       @RequestParam(name="sort2", required = false) String sort2,
                       @RequestParam(name="sort3", required = false) String sort3) {

        return home(model, 1, "personnel_number", "asc", string, position, department, sort2, sort3);
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
        model.addAttribute("employees", certificationService.getCertificationStatistic(year));
        return "statistic/certification_employee";
    }

    @GetMapping("/statistic/infraction/employee")
    public String showStatisticInfraction(@RequestParam(name="year", required = false) Integer year, Model model){
        model.addAttribute("employees", infractionService.getInfractionStatistic(year));
        return "statistic/infraction_employee";
    }

}
