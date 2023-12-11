package ru.egar.dto.document;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DismissalDTO {

    @NotNull(message = "Причина не должна быть пустой")
    private Long reason;

    @NotBlank(message = "Описаное должно быть указано")
    @Size(max=100)
    private String description;
}
