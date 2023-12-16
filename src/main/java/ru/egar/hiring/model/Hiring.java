package ru.egar.hiring.model;

import lombok.Data;
import ru.egar.department.model.Department;
import ru.egar.document.model.Document;
import ru.egar.position.model.Position;

import javax.persistence.*;
//найм сотрудника
@Entity
@Data
@Table(name = "hiring")
public class Hiring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_document", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk"))
    private Document idDocument;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_position", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk_1"))
    private Position idPosition;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_department", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hiring_fk_2"))
    private Department idDepartment;

}
