package ru.egar.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.enums.Grade;

import javax.persistence.*;
import java.time.LocalDate;

//а
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

    @Override
    public String toString() {
        return "Certification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", grade=" + grade.getResult() +
                ", idEmployee=" + idEmployee.getId() +
                '}';
    }
}

