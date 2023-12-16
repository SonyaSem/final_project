package ru.egar.document.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ru.egar.dismissal.model.Dismissal;
import ru.egar.enums.DocumentType;
import ru.egar.employee.model.Employee;
import ru.egar.hiring.model.Hiring;
import ru.egar.transfer.model.Transfer;

import javax.persistence.*;
import java.time.LocalDate;


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

}
