package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.dto.InfractionMonth;
import ru.egar.model.Infraction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InfractionRepository extends JpaRepository<Infraction, Long> {

    //NamedNativeQuery
    @Query( nativeQuery = true)
    List<InfractionMonth> listInfractionMonth(@Param("year") Integer year);

    //Нарушения всех сотрудников
    @Query(value = "select e.personnel_number as personnelNumber, e.last_name as lastName, " +
            "e.first_name as firstName, e.second_name as secondName, i.description, i.date, i.punishment  " +
            "from employee e right join infraction i on i.id_employee =e.id" +
            " order by i.date desc", nativeQuery = true)
    List<InfractionInfoDTO> listInfraction();

    interface InfractionInfoDTO{
        String getPersonnelNumber();
        String getLastName();
        String getFirstName();
        String getSecondName();
        String getDescription();
        String getDate();
        String getPunishment();
    }

    //ВСе нарушения сотрудника
    @Query(value = "select description, date, punishment from infraction i " +
            "where id_employee = (select e.id from employee e where e.personnel_number=:personnelNumber) " +
            "order by i.date desc", nativeQuery = true)
    List<EmployeeInfractionDTO> listEmployeeInfraction(@Param("personnelNumber") Integer number);

    interface EmployeeInfractionDTO{
        String getDescription();
        LocalDate getDate();
        String getPunishment();
    }

}
