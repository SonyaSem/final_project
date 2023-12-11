package ru.egar.model;


import lombok.Data;

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

    @OneToMany(mappedBy = "idReason", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dismissal> dismissals;

    @OneToMany(mappedBy = "idReason", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transfer> transfers;


}
