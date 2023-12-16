package ru.egar.dismissal.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egar.reason.model.Reason;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DismissalDTO {

    private Long id;
    @NotNull(message = "Причина не должна быть пустой")
    private Long reason;

    @NotBlank(message = "Описаное должно быть указано")
    @Size(max=100)
    private String description;
}
