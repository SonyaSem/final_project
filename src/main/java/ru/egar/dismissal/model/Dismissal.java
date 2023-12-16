package ru.egar.dismissal.model;

import lombok.Data;
import lombok.ToString;
import ru.egar.document.model.Document;
import ru.egar.reason.model.Reason;

import javax.persistence.*;

//увольнение сотрудника
@Data
@Entity
@Table(name = "dismissal")
public class Dismissal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_document", referencedColumnName = "id", foreignKey = @ForeignKey(name = "dismissal_fk"))
    private Document idDocument;

   // @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_reason", referencedColumnName = "id", foreignKey = @ForeignKey(name = "dismissal_fk_1"))
    private Reason idReason;

}
