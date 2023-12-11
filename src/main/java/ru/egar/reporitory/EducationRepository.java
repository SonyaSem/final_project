package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.model.Education;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

    //Запрос на получение образований сотрудника
    @Query(value = "select e.name, e.finish_date as date, e.university, s.name as speciality " +
            "from education e left join speciality s on s.id = e.id_speciality " +
            "where e.id_employee = (select id from employee  where personnel_number=:number) " +
            "order by e.finish_date desc", nativeQuery = true)
    List<EmployeeEducation> listEmployeeEducation(@Param("number") Integer number);

    interface EmployeeEducation{
        String getName();
        String getDate();
        String getUniversity();
        String getSpeciality();
    }

}
