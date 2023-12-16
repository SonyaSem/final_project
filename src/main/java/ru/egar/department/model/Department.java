package ru.egar.department.model;

import lombok.Data;
import lombok.ToString;
import ru.egar.hiring.model.Hiring;
import ru.egar.transfer.model.Transfer;

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

  @ToString.Exclude
  @OneToMany(mappedBy = "idDepartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Hiring> hirings;

  @ToString.Exclude
  @OneToMany(mappedBy = "idDepartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Transfer> transfers;

}
