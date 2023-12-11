package ru.egar.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Certification> certifications;

    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Infraction> infractions;

    @OneToMany(mappedBy = "idEmployee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Education> educations;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", personnelNumber=" + personnelNumber +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
