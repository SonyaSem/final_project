package ru.egar.certification.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.egar.certification.dto.CertificationDTO;
import ru.egar.certification.service.CertificationService;
import ru.egar.employee.service.EmployeeService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;
    private final EmployeeService employeeService;

    @GetMapping("/certification")
    public String certificate(Model model) {
        model.addAttribute("certification", certificationService.getListCertification());
        return "certification";
    }


    @GetMapping("/employee/{personnelNumber}/certification")
    public String addCertification(@PathVariable Integer personnelNumber, Model model)
    {
        model.addAttribute("certification", new CertificationDTO());
        return "add_certification";
    }

    @PostMapping("/employee/{personnelNumber}/certification")
    public String createCertification(@PathVariable Integer personnelNumber,
                                      @ModelAttribute("certification") @Valid CertificationDTO certification,
                                      BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("certification", certification);
            return "add_certification";
        }

        certification.setEmployeeNumber(personnelNumber);
        certificationService.saveCertification(certification);
        return "redirect:/employee/{personnelNumber}";
    }

    @GetMapping("/statistic/certification/grade")
    public String showStatisticGrade(@RequestParam(name="year", required = false) Integer year, Model model){

        model.addAttribute("certifications", certificationService.getListStatisticGrade(year));
        return "statistic/certification_grade";
    }

}
