package ru.egar.employee.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.certification.model.Certification;
import ru.egar.document.model.Document;
import ru.egar.education.model.Education;
import ru.egar.infraction.model.Infraction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer personnelNumber;

    @Column(length = 25)
    private String lastName;

    @Column(length = 20)
    private String firstName;

    @Column(length = 25)
    private String secondName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(length = 12)
    private String phone;

    @Column(length = 50)
    private String address;

    @ToString.Exclude
    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> documents;

    @ToString.Exclude
    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Certification> certifications;

    @ToString.Exclude
    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Infraction> infractions;

    @ToString.Exclude
    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Education> educations;

}
