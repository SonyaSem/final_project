package ru.egar.certification.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CertificationInfoDTO {

    private Integer personnelNumber;
    private String lastName;
    private String firstName;
    private String secondName;
    private LocalDate date;
    private String grade;
    private String name;

}
