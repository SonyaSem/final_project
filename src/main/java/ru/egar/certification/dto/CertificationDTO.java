package ru.egar.certification.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.enums.Grade;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CertificationDTO {

    private Long id;
    @NotBlank(message = "Название должно быть указано")
    @Size(max= 100, message = "В названии должно быть не больше 100 символов")
    private String name;

    @NotNull(message = "Дата должна быть указана")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Оценка должна быть указана")
    private Grade grade;

    private Integer employeeNumber;
}
