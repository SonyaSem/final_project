package ru.egar.employee.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeInfoDTO {
    private Integer personnelNumber;
    private String lastName;
    private String firstName;
    private String secondName;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private String position;
    private String department;
    private Integer salary;
}
