package ru.egar.model;

import lombok.Data;
import ru.egar.enums.DocumentType;

import javax.persistence.*;
import java.util.List;

//отдел
@Entity
@Table(name = "department")
@Data
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 30)
  private String name;

  @OneToMany(mappedBy = "idDepartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Hiring> hirings;

  @OneToMany(mappedBy = "idDepartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Transfer> transfers;

}
