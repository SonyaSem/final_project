package ru.egar.education.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.education.model.Education;

import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    //Запрос на получение образований сотрудника
    @Query(value = "select * from education e " +
            "where e.id_employee = (select id from employee  where personnel_number=:number) " +
            "order by e.finish_date desc", nativeQuery = true)
    List<Education> listEmployeeEducation(@Param("number") Integer number);

}
