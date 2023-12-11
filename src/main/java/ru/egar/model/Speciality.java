package ru.egar.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

//Специальность
@Entity
@Data
@Table(name = "speciality")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String name;

    @Column(length = 12)
    private String code;

    @OneToMany(mappedBy = "idSpeciality", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Education> educations;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "speciality_position",
            joinColumns = @JoinColumn(name = "id_speciality"),
            inverseJoinColumns = @JoinColumn(name = "id_position"))
    private List<Position> positions;


}
