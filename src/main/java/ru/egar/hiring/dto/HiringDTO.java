package ru.egar.hiring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiringDTO {

    private Long id;
    @NotNull
    private Long department;
    @NotNull
    private Long position;
}
