package ru.egar.transfer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TransferDTO {

    private Long id;
    @NotNull(message = "Отдел должен быть указан")
    private Long department;
    @NotNull(message = "Должность должна быть указана")
    private Long position;
    @NotNull(message = "Причина не должна быть пустой")
    private Long reason;

}
