package ru.egar.infraction.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeInfractionDTO {
    private Integer personnelNumber;
    private String lastName;
    private String firstName;
    private String secondName;
    private Integer count;
    private BigDecimal percentage;
}
