package ru.egar.education.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egar.education.dto.EducationDTO;
import ru.egar.speciality.repo.SpecialityRepository;
import ru.egar.education.service.EducationService;
import ru.egar.employee.service.EmployeeService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;
    private final EmployeeService employeeService;

    private final SpecialityRepository specialityRepository;

    @GetMapping("/employee/{personnelNumber}/education")
    public String addEducation(@PathVariable Integer personnelNumber, Model model) {
        model.addAttribute("specialities", specialityRepository.findAll());
        model.addAttribute("education", new EducationDTO());

        return "add_education";
    }

    @PostMapping("/employee/{personnelNumber}/education")
    public String createEducation(@PathVariable Integer personnelNumber,
                                   @ModelAttribute("education") @Valid EducationDTO education, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("education", education);
            model.addAttribute("specialities", specialityRepository.findAll());
            return "add_education";
        }

        education.setEmployee(personnelNumber);
        educationService.saveEducation(education);
        return "redirect:/employee/{personnelNumber}";
    }

}
