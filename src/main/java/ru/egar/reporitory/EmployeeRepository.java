package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    Optional<Employee> findByPersonnelNumber(Integer personnelNumber);

    Optional<Employee> findById(Long id);

    //Статистика по аттестациям сотрудников
    @Query(value = "select e.personnel_number as personnelNumber, " +
            " e.last_name as lastName, e.first_name as firstName," +
            " e.second_name as secondName, count(1) as totalCount, " +
            " count(1) filter (where c.grade !='плохо') as passCount,  " +
            " round(count(1) filter (where c.grade!='плохо')*100/count(1),2) as percentage " +
            " from employee e left join certification c " +
            " on c.id_employee = e.id " +
            " where c.date IS NOT null and (:year is null or EXTRACT(year FROM c.date) = :year)" +
            " group by e.personnel_number, e.last_name, e.first_name, e.second_name", nativeQuery = true)
    List<CertificationInfoEmployee> listCertificationEmployee(@Param("year") Integer year);

    interface CertificationInfoEmployee{
        String getPersonnelNumber();
        String getLastName();
        String getFirstName();
        String getSecondName();
        Integer getTotalCount();
        Integer getPassCount();
        BigDecimal getPercentage();

    }

    // Статистика нарушеий по сотрудникам
    @Query(value = "select e.personnel_number as personnelNumber," +
            " e.last_name as lastName, e.first_name as firstName," +
            " e.second_name as secondName, COUNT(i.id), " +
            "round(count(i.id) * 100/ SUM(count(*)) Over(), 2) as percentage" +
            " from employee e left join infraction i on i.id_employee = e.id " +
            " WHERE i.date IS NOT null and (:year is null or EXTRACT(year FROM i.date) = :year) " +
            "GROUP BY e.personnel_number, e.first_name, e.second_name, e.last_name", nativeQuery = true)
    List<InfractionEmployee> listInfractionEmployee(@Param("year") Integer year);

    interface InfractionEmployee{
        String getPersonnelNumber();
        String getLastName();
        String getFirstName();
        String getSecondName();
        Integer getCount();
        BigDecimal getPercentage();
    }

    //Вывод сотрудников с их должностями и отделами
    @Query(value = "select e.id, e.personnel_number as personnelNumber, e.last_name as lastName, e.first_name as firstName, " +
            "e.second_name as secondName, e.birth_date as birthDate, e.phone, e.address, p.name as position, dep.name  as department from employee e " +
            "left join document d on d.id_employee = e.id and d.id in (select distinct on(doc.id_employee)doc.id " +
            "from document doc ORDER BY doc.id_employee, doc.create_date desc)" +
            "left join position p on (p.id = (select h.id_position from hiring h where h.id_document = d.id) " +
            "or p.id = (select t.id_position from transfer t where t.id_document = d.id)) " +
            "left join department dep on (dep.id = (select h.id_department  from hiring h where h.id_document = d.id) " +
            "or dep.id = (select t.id_department  from transfer t where t.id_document = d.id))",
            nativeQuery = true)
    List<EmployeeInfoDTO> listEmployee();


    @Query(value = "select e.id, e.personnel_number as personnelNumber, e.last_name as lastName, e.first_name as firstName, " +
            "e.second_name as secondName, e.birth_date as birthDate, e.phone, e.address, p.name as position, dep.name  as department from employee e " +
            "left join document d on d.id_employee = e.id and d.id in (select distinct on(doc.id_employee)doc.id " +
            "from document doc ORDER BY doc.id_employee, doc.create_date desc)" +
            "left join position p on (p.id = (select h.id_position from hiring h where h.id_document = d.id) " +
            "or p.id = (select t.id_position from transfer t where t.id_document = d.id)) " +
            "left join department dep on (dep.id = (select h.id_department  from hiring h where h.id_document = d.id) " +
            "or dep.id = (select t.id_department  from transfer t where t.id_document = d.id))" +
            " where (:number is null or e.personnel_number = :number) and (:lastName is null or e.last_name ilike %:lastName%) " +
            "and (:firstName is null or e.first_name ilike %:firstName%) and (:secondName is null or e.second_name ilike %:secondName%) " +
            "and (:phone is null or e.phone = :phone)",
            nativeQuery = true)
    List<EmployeeInfoDTO> listEmployee(@Param("number") Integer number, @Param("lastName") String lastName, @Param("firstName") String firstName,
                                   @Param("secondName") String secondName, @Param("phone") String phone);



    @Query(value = "select e.id, e.personnel_number as personnelNumber, e.last_name as lastName, e.first_name as firstName, " +
            "e.second_name as secondName, e.birth_date as birthDate, e.phone, e.address, p.name as position, dep.name  as department from employee e " +
            "left join document d on d.id_employee = e.id and d.id in (select distinct on(doc.id_employee)doc.id " +
            "from document doc ORDER BY doc.id_employee, doc.create_date desc)" +
            "left join position p on (p.id = (select h.id_position from hiring h where h.id_document = d.id) " +
            "or p.id = (select t.id_position from transfer t where t.id_document = d.id)) " +
            "left join department dep on (dep.id = (select h.id_department  from hiring h where h.id_document = d.id) " +
            "or dep.id = (select t.id_department  from transfer t where t.id_document = d.id))" +
            "where (:position is null or p.name=:position) and (:department is null or dep.name = :department) " +
            " and ((:position is not null and :department is not null) or(:position is not null and :department is null) or (:position is null and :department is not null) or (p.name is null and dep.name is null))",
            nativeQuery = true)
    List<EmployeeInfoDTO> listEmployee(@Param("position") String position,
                                       @Param("department") String department);


    interface EmployeeInfoDTO{
        Integer getId();
        String getPersonnelNumber();
        String getLastName();
        String getFirstName();
        String getSecondName();
        String getBirthDate();
        String getPhone();
        String getAddress();
        String getPosition();
        String getDepartment();
    }

    //Проверка сотрудника на отстутсвие нарушений в течении года
    @Query(value = "select not exists (select i.id_employee from infraction i where i.date > current_date - interval '1 year' " +
            "and i.id_employee = (select id from employee where personnel_number = :personnelNumber))", nativeQuery = true)
    Boolean checkInfractions(@Param("personnelNumber") Integer personnelNumber);

    //Проверка сотрудника на получение 3 последних аттестаций с оценкой "хорошо"
    @Query(value = "select exists (select c.id_employee from certification c where c.grade ='хорошо' " +
            "and c.id_employee = (select id from employee where personnel_number=:personnelNumber) " +
            "group by c.id_employee HAVING COUNT(c.id_employee)>2 order by c.id_employee desc limit 3)", nativeQuery = true)
    Boolean checkGoodCertification(@Param("personnelNumber") Integer personnelNumber);

    //Проверка сотрудника на получение образования или последней аттестации (меньше 3 лет)
    @Query(value = "select exists (select c.id_employee from certification c where c.date < current_date - interval '3 year' and c.id_employee=(select id from employee where personnel_number=:personnelNumber)) " +
            "or exists(select ed.id_employee from education ed where ed.finish_date  < current_date  - interval '3 year' " +
            "and ed.id_employee=(select id from employee where personnel_number=:personnelNumber))", nativeQuery = true)
    Boolean checkLastEducationAndCertification(@Param("personnelNumber") Integer personnelNumber);

    //Проверка сотрудника на совершение 3 нарушений за месяц
    @Query(value = "select e.id, e.personnel_number as personnelNumber, e.last_name as lastName, e.first_name as firstName, " +
            "e.second_name as secondName, e.birth_date as birthDate, e.phone, e.address, p.name as position, dep.name  as department from employee e " +
            "left join document d on d.id_employee = e.id and d.id in (select distinct on(doc.id_employee)doc.id from document doc ORDER BY doc.id_employee, doc.create_date desc) " +
            "left join position p on (p.id = (select h.id_position from hiring h where h.id_document = d.id) or p.id = (select t.id_position from transfer t where t.id_document = d.id)) " +
            "left join department dep on (dep.id = (select h.id_department  from hiring h where h.id_document = d.id) or dep.id = (select t.id_department  from transfer t where t.id_document = d.id)) " +
            "where e.id in (select infraction.id_employee from infraction  where infraction.date > current_date  - interval '1 month' group by infraction.id_employee HAVING COUNT(infraction.id_employee)>2)", nativeQuery = true)
    List<EmployeeInfoDTO> checkForDismissal();

}
