package ru.egar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.egar.dto.document.DismissalDTO;
import ru.egar.dto.document.DocumentDTO;
import ru.egar.dto.document.HiringDTO;
import ru.egar.dto.document.TransferDTO;
import ru.egar.enums.DocumentType;
import ru.egar.reporitory.DepartmentRepository;
import ru.egar.reporitory.ReasonRepository;
import ru.egar.service.DocumentService;
import ru.egar.service.EmployeeService;
import ru.egar.service.PositionService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final PositionService positionService;
    private final DepartmentRepository departmentRepository;
    private final ReasonRepository reasonRepository;
    private final EmployeeService employeeService;

    @GetMapping("/doc")
    public String doc(Model model) {
        model.addAttribute("documents", documentService.listDocument());
        return "document/document";
    }

    @GetMapping("/employee/{personnelNumber}/hiring")
    public String addHiring(@PathVariable Integer personnelNumber, Model model) {

        model.addAttribute("positions", positionService.getSuitablePosition(personnelNumber));
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("document", new DocumentDTO());
        model.addAttribute("hiring", new HiringDTO());
        return "document/hiring";
    }

    @GetMapping("/employee/{personnelNumber}/transfer")
    public String addTransfer(@PathVariable Integer personnelNumber,
                              @RequestParam(required = false) boolean condition1,
                              @RequestParam(required = false) boolean condition2,
                              @RequestParam(required = false) boolean condition3,
                              Model model, RedirectAttributes redirectAttributes) {

        boolean check1 = true;
        boolean check2 = true;
        boolean check3 = true;

        if(condition1){
            check1 = employeeService.getCheckLastEducationAndCertification(personnelNumber);
            if(!check1){
                redirectAttributes.addFlashAttribute("error1", "Образование или аттестация сотрудника было получено более 3 лет назад ");
            }
        }
        if(condition2){
            check2 = employeeService.getCheckInfractions(personnelNumber);
            if(!check2){
                redirectAttributes.addFlashAttribute("error2", "У сотрудника были нарушения за год ");
            }
        }
        if(condition3){
            check3 = employeeService.getCheckGoodCertification(personnelNumber);
            if(!check3){
                redirectAttributes.addFlashAttribute("error3", "У сотрудника не было 3 последних аттестаций с оценкой 'хорошо' ");
            }
        }

        if(check1 && check2 && check3 ){
            model.addAttribute("positions", positionService.getSuitablePosition(personnelNumber));
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("reasons", reasonRepository.findAll());
            model.addAttribute("document", new DocumentDTO());
            model.addAttribute("transfer", new TransferDTO());
            return "document/transfer";
        }

        return "redirect:/employee/{personnelNumber}/";
    }

    @GetMapping("/employee/{personnelNumber}/dismissal")
    public String addDismissal(@PathVariable Integer personnelNumber, Model model) {
        model.addAttribute("reasons", reasonRepository.findAll());
        model.addAttribute("document", new DocumentDTO());
        model.addAttribute("dismissal", new DismissalDTO());
        return "document/dismissal";
    }

    @PostMapping("/employee/{personnelNumber}/hiring")
    public String createHiringDocument(@PathVariable Integer personnelNumber, @ModelAttribute("document") @Valid DocumentDTO document, BindingResult documentResult,
                                       @ModelAttribute("hiring") @Valid HiringDTO hiring, BindingResult hiringResult, Model model) {

        if(documentResult.hasErrors()|| hiringResult.hasErrors()){
            model.addAttribute("document", document);
            model.addAttribute("hiring", hiring);
            model.addAttribute("positions", positionService.getSuitablePosition(personnelNumber));
            model.addAttribute("departments", departmentRepository.findAll());
            return "document/hiring";
        }

        document.setType(DocumentType.HIRING);
        document.setPersonnelNumber(personnelNumber);
        documentService.saveHiring(document, hiring);

        return "redirect:/employee/{personnelNumber}";
    }

    @PostMapping("/employee/{personnelNumber}/transfer")
    public String createTransferDocument(@PathVariable Integer personnelNumber, @ModelAttribute("document") @Valid DocumentDTO document, BindingResult documentResult,
                                         @ModelAttribute("transfer") @Valid TransferDTO transfer, BindingResult transferResult, Model model) {
        if(documentResult.hasErrors()|| transferResult.hasErrors()){
            model.addAttribute("document", document);
            model.addAttribute("transfer", transfer);
            model.addAttribute("positions", positionService.getSuitablePosition(personnelNumber));
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("reasons", reasonRepository.findAll());

            return "document/transfer";
        }

        document.setType(DocumentType.TRANSFER);
        document.setPersonnelNumber(personnelNumber);
        documentService.saveTransfer(document, transfer);

        return "redirect:/employee/{personnelNumber}";
    }

    @PostMapping("/employee/{personnelNumber}/dismissal")
    public String createDismissalDocument(@PathVariable Integer personnelNumber, @ModelAttribute("document") @Valid DocumentDTO document, BindingResult documentResult,
                                          @ModelAttribute("dismissal") @Valid DismissalDTO dismissal, BindingResult dismissalResult, Model model) {
        if(documentResult.hasErrors() || dismissalResult.hasErrors()){
            model.addAttribute("document", document);
            model.addAttribute("dismissal", dismissal);
            model.addAttribute("reasons", reasonRepository.findAll());
            return "document/dismissal";
        }

        document.setType(DocumentType.DISMISSAL);
        document.setPersonnelNumber(personnelNumber);
        documentService.saveDismissal(document, dismissal);
        return "redirect:/employee/{personnelNumber}";
    }

}