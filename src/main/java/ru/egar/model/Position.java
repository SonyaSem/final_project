package ru.egar.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


//Должность
@Entity
@Data
@Table(name="position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;
    private Integer salary;

    @OneToMany(mappedBy = "idPosition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Hiring> hirings;

    @OneToMany(mappedBy = "idPosition", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transfer> transfers;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "speciality_position",
            joinColumns = @JoinColumn(name = "id_position"),
            inverseJoinColumns = @JoinColumn(name = "id_speciality"))
    private List<Speciality> specialities;


}
