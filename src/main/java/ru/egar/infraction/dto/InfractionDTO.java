package ru.egar.infraction.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class InfractionDTO {

    private Long id;
    @NotBlank(message = "Описание должно быть указано")
    @Size(max= 200, message = "В описание должно быть не больше 200 символов")
    private String description;

    @NotNull(message = "Дата должна быть указана")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "Наказание должно быть указано")
    @Size(max= 100, message = "В наказании должно быть не больше 100 символов")
    private String punishment;

    private Integer employee;

}
