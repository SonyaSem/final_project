package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.model.Certification;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long> {

    //запрос для получения статистикии по оценкам
    @Query(value ="select grade, count(id) as count, round(count(id) * 100/ SUM(count(*)) Over(), 2) as percentage" +
            " from certification c " +
            "where :year is null or EXTRACT(year FROM date) = :year " +
            "group by grade", nativeQuery = true)
    List<CertificationGarde> listCertificationGrade(@Param("year") Integer year);

    interface CertificationGarde{
        String getGrade();
        Integer getCount();
        BigDecimal getPercentage();
    }

    //запрос на получение аттестаций сотрудника
    @Query(value = "select name, date, grade from certification c " +
            " where id_employee = (select e.id from employee e where e.personnel_number=:number) " +
            "order by date desc", nativeQuery = true)
    List<EmployeeCertificationDTO> listEmployeeCertification(@Param("number") Integer number);

    interface EmployeeCertificationDTO{
        String getName();
        String getDate();
        String getGrade();
    }

    //Запрос на получение аттестаций всех сотрудников
    @Query(value = "select e.personnel_number as personnelNumber, e.last_name as lastName," +
            " e.first_name as firstName, e.second_name as secondName, c.name , c.date, c.grade  " +
            "from employee e right join certification c  on c.id_employee =e.id order by c.date desc ", nativeQuery = true)
    List<CertificationInfoDTO> listCertification();

    interface CertificationInfoDTO{
        String getPersonnelNumber();
        String getLastName();
        String getFirstName();
        String getSecondName();
        String getName();
        String getDate();
        String getGrade();
    }
}
