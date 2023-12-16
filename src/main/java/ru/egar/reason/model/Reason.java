package ru.egar.reason.model;


import lombok.Data;
import lombok.ToString;
import ru.egar.dismissal.model.Dismissal;
import ru.egar.transfer.model.Transfer;

import javax.persistence.*;
import java.util.List;

//Причина увольнения или перевода
@Data
@Entity
@Table(name = "reason")
public class Reason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "idReason", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dismissal> dismissals;

    @ToString.Exclude
    @OneToMany(mappedBy = "idReason", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transfer> transfers;


}
