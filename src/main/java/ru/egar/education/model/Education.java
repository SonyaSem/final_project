package ru.egar.education.model;


import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.employee.model.Employee;
import ru.egar.speciality.model.Speciality;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate finishDate;

    @Column(length = 60)
    private String university;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_speciality", referencedColumnName = "id", foreignKey = @ForeignKey(name = "education_fk"))
    private Speciality idSpeciality;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", foreignKey = @ForeignKey(name = "education_fk_1"))
    private Employee idEmployee;

}
