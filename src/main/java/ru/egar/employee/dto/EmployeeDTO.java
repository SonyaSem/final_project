package ru.egar.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "Номер должен быть указан")
    @Max(value = 9999, message = "В номере должно быть 4 цифры")
    @Min(value = 1000, message = "В номере должно быть 4 цифры")
    private Integer personnelNumber;

    @NotBlank(message = "Имя должно быть указано")
    @Size(max = 20, message = "В имени не должно быть больше 20 символов")
    private String firstName;

    @NotBlank(message = "Отчество должно быть указано")
    @Size(max = 25, message = "В отчестве не должно быть больше 25 символов")
    private String secondName;

    @NotBlank(message = "Фамилия должна быть указана")
    @Size(max = 25, message = "В фамилии не должно быть больше 20 символов")
    private String lastName;

    @NotNull(message = "Дата должна быть указана")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Телефон должен быть указан")
    @Pattern(regexp = "[8][0-9]{10}", message = "Телефон должен быть в правильном формате")
    private String phone;
    @NotBlank(message = "Адрес должен быть указан")
    @Size(max = 50, message = "Длина адреса не должна быть больше 50 символов")
    private String address;
}
