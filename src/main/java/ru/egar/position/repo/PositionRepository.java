package ru.egar.position.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.position.model.Position;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {


    Optional<Position> findById(Long id);

    //Должности, на которые можно нанять сотрудника, по его специальности в образовании
    @Query(value ="select * from position"
           + " where position.id in (select id_position from speciality_position"
            +" where speciality_position.id_speciality in (select id_speciality from education"
           + " where education.id_employee = :id)) " ,nativeQuery = true)
    List<Position> getPosition(@Param("id")Long id);

    //Должности, на которые можно нанять сотрудника, по его специальности в образовании
    @Query(value ="select * from position"
            + " where position.id in (select id_position from speciality_position"
            +" where speciality_position.id_speciality in (select id_speciality from education"
            + " where education.id_employee = (select id from employee where employee.personnel_number =:personnelNumber)))" ,
            nativeQuery = true)
    List<Position> getPosition(@Param("personnelNumber")Integer personnelNumber);


}
