package ru.egar.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    @NotBlank(message = "Название должно быть указано")
    @Size(max= 100, message = "В названии должно быть не больше 100 символов")
    private String name;

    @NotNull(message = "Дата окончания должна быть указана")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate finishDate;

    @NotBlank(message = "Университет должен быть указан")
    @Size(max= 60, message = "В названии университета должно быть не больше 60 символов")
    private String university;

    @NotNull(message = "Специальность должна быть заполнена")
    private Long speciality;

    private Integer employee;

}
