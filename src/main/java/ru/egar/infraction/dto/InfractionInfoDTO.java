package ru.egar.infraction.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InfractionInfoDTO {
    private Integer personnelNumber;
    private String lastName;
    private String firstName;
    private String secondName;
    private LocalDate date;
    private String description;
    private String punishment;
}
