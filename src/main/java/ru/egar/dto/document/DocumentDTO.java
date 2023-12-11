package ru.egar.dto.document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.enums.DocumentType;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DocumentDTO {

    @NotNull(message = "Номер должен быть указан")
    @Max(value = 999999, message = "В номере должно быть 6 цифр")
    @Min(value = 100000, message = "В номере должно быть 6 цифр")
    private Integer number;

    @NotBlank(message = "Название договора должно быть указано")
    @Size(max= 40, message = "В названии документа должно быть не больше 40 символов")
    private String name;

    private DocumentType type;

    @NotNull(message = "Датасоздания договора должна быть указана")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate createDate;

    @NotNull(message = "Дата исполнения договора должна быть указана")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    private Integer personnelNumber;
}
