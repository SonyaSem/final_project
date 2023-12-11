package ru.egar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egar.dto.EducationDTO;
import ru.egar.reporitory.SpecialityRepository;
import ru.egar.service.EducationService;
import ru.egar.service.EmployeeService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;
    private final EmployeeService employeeService;

    ////
    private final SpecialityRepository specialityRepository;
///
/*
    @GetMapping("/education")
    public String education(Model model) {
        model.addAttribute("edcucations", educationService.listEducation());

        return "education";
    }*/

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
