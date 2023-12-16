package ru.egar.infraction.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.infraction.dto.InfractionMonth;
import ru.egar.infraction.model.Infraction;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InfractionRepository extends JpaRepository<Infraction, Long> {

    //NamedNativeQuery
    @Query( nativeQuery = true)
    List<InfractionMonth> listInfractionMonth(@Param("year") Integer year);

    //Нарушения всех сотрудников
    @Query(value = "select * from infraction i order by i.date desc", nativeQuery = true)
    List<Infraction> listInfraction();


    //ВСе нарушения сотрудника
    @Query(value = "select * from infraction i " +
            "where id_employee = (select e.id from employee e where e.personnel_number=:personnelNumber) " +
            "order by i.date desc", nativeQuery = true)
    List<Infraction> listEmployeeInfraction(@Param("personnelNumber") Integer number);

    // Статистика нарушеий по сотрудникам
    @Query(value = "select  i.id_employee as employee, COUNT(i.id) as count, round(count(i.id) * 100/ SUM(count(*)) Over(), 2) as percentage  "+
            "from infraction i  WHERE i.date IS NOT null and (:year is null or EXTRACT(year FROM i.date) = :year) GROUP by i.id_employee ", nativeQuery = true)
    List<InfractionEmployee> listInfractionEmployee(@Param("year") Integer year);

    interface InfractionEmployee{
        Long getEmployee();
        Integer getCount();
        BigDecimal getPercentage();
    }
}
