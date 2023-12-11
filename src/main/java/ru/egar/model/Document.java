package ru.egar.model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.enums.DocumentType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "document")
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @Column(length = 40)
    private String name;
    private DocumentType type;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate createDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee", referencedColumnName = "id", foreignKey = @ForeignKey(name = "document_fk"))
    private Employee idEmployee;

     @OneToOne(mappedBy = "idDocument")
     private Hiring hiring;

    @OneToOne(mappedBy = "idDocument")
    private Transfer transfer;

    @OneToOne(mappedBy = "idDocument")
    private Dismissal dismissal;

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", type=" + type.getType() +
                ", createDate=" + createDate +
                ", startDate=" + startDate +
                ", idEmployee=" + idEmployee.getId() +
                '}';
    }
}
