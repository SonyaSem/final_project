package ru.egar.certification.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeCertificationDTO {
    private Integer personnelNumber;
    private String lastName;
    private String firstName;
    private String secondName;
    private Integer total;
    private Integer passCount;
    private BigDecimal percentage;
}
