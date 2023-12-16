package ru.egar.certification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.certification.model.Certification;
import ru.egar.employee.repo.EmployeeRepository;

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
    @Query(value = "select * from certification c " +
            " where id_employee = (select e.id from employee e where e.personnel_number=:number) " +
            "order by date desc", nativeQuery = true)
    List<Certification> listEmployeeCertification(@Param("number") Integer number);


    //Запрос на получение аттестаций всех сотрудников
    @Query(value = "select *  from certification c  order by c.date desc ", nativeQuery = true)
    List<Certification> listCertification();


    @Query(value = "select * from certification c where c.id_employee =e.id order by c.date desc ", nativeQuery = true)
    List<Certification> findAll();

    //Статистика по аттестациям сотрудников
    @Query(value = "select c.id_employee as employee, count(1) as total, count(1) filter (where c.grade !='плохо') as passCount, " +
            "round(count(1) filter (where c.grade!='плохо')*100/count(1),2) as percentage from certification c " +
            "where c.date IS NOT null and (:year is null or EXTRACT(year FROM c.date)=:year) group by c.id_employee", nativeQuery = true)
    List<EmployeeCertification> listCertificationEmployee(@Param("year") Integer year);

    interface EmployeeCertification{
        Long getEmployee();
        Integer getTotal();
        Integer getPassCount();
        BigDecimal getPercentage();
    }

}
