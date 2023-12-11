package ru.egar.dto.document;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {
    private long id;

    private String personnelNumber;
    private String firstName;
    private String secondName;
    private String lastName;

    private Integer number;
    private String name;
    private String type;

    private LocalDate startDate;
    private LocalDate createDate;

    private String department;
    private String position;

    private String reason;
    private String description;

}
