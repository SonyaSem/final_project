package ru.egar.certification.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.enums.Grade;
import ru.egar.employee.model.Employee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    private Grade grade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", foreignKey = @ForeignKey(name = "certification_fk"))
    private Employee idEmployee;

}

