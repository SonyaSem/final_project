package ru.egar.transfer.model;

import lombok.Data;
import ru.egar.department.model.Department;
import ru.egar.document.model.Document;
import ru.egar.position.model.Position;
import ru.egar.reason.model.Reason;

import javax.persistence.*;

//перевод сотрудника
@Data
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_document", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk"))
    private Document idDocument;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reason", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk_1"))
    private Reason idReason;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_position", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk_2"))
    private Position idPosition;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_department", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk_3"))
    private Department idDepartment;
}
