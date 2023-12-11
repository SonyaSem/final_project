package ru.egar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egar.dto.InfractionDTO;
import ru.egar.service.EmployeeService;
import ru.egar.service.InfractionService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class InfractionController {

    private final InfractionService infractionService;
    private final EmployeeService employeeService;

    @PostMapping("/employee/{personnelNumber}/infraction")
    public String createInfraction(@PathVariable Integer personnelNumber,
                                   @ModelAttribute("infraction") @Valid InfractionDTO infraction,
                                   BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("infraction", infraction);
            return "add_infraction";
        }

        infraction.setEmployee(personnelNumber);
        infractionService.saveInfraction(infraction);
        return "redirect:/employee/{personnelNumber}";

    }

    @GetMapping("/employee/{personnelNumber}/infraction")
    public String addInfraction(@PathVariable Integer personnelNumber, Model model) {
        model.addAttribute("infraction", new InfractionDTO());
        return "add_infraction";
    }


    @GetMapping("/infraction")
    public String home(Model model) {
        model.addAttribute("infractions", infractionService.getLisInfraction());
        return "infraction";
    }

    @GetMapping("/statistic/infraction/month")
    public String showStatisticInfraction(@RequestParam(name="year", required = false) Integer year, Model model){
        model.addAttribute("infractions", infractionService.getStaticInfraction(year));
        return "statistic/infraction_month";
    }

}
