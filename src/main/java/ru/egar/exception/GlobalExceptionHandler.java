package ru.egar.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public String handleException(NotFoundException exception, Model model) {
        log.error("Ошибка: {}", exception.getMessage());
        model.addAttribute("error",exception.getMessage());
       return "error";
    }

}