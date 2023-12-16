package ru.egar.employee.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.employee.dto.EmployeeInfoDTO;
import ru.egar.employee.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    Optional<Employee> findByPersonnelNumber(Integer personnelNumber);


    Optional<Employee> findById(Long id);

    //Вывод сотрудников с их должностями и отделами
      @Query(value = "select * from employee where ?1 is null or concat(cast( personnel_number as varchar), first_name, second_name, last_name, phone, address) ilike %?1%",
            nativeQuery = true)

    Page<Employee> listEmployee(String string, Pageable pageable);


    @Query(value = "select * from employee e where e.id in (select doc.id_employee from document doc where doc.create_date = (select max(create_date) from document where id_employee = e.id) " +
            "and (doc.id in (select h.id_document from hiring h where h.id_position in (select id from position where (:position is null or name = :position)))" +
            "or doc.id in (select t.id_document from transfer t where t.id_position in (select id from position where (:position is null or name = :position))))" +
            "and (doc.id in (select h.id_document from hiring h where h.id_department in (select id from department where (:department is null or name = :department))) " +
            "or doc.id in (select t.id_document from transfer t where t.id_department in (select id from department where (:department is null or name = :department)))))", nativeQuery = true)
    Page<Employee> listEmployee(@Param("position") String position,
                                       @Param("department") String department, Pageable pageable);


    @Query(value = "select * from employee e where not e.id in (select doc.id_employee from document doc where doc.create_date = (select max(create_date) " +
            "from document where id_employee = e.id)" +
            "and (doc.type = 'перевод' or doc.type = 'найм'))", nativeQuery = true)
    Page<Employee> listNotEmployee(Pageable pageable);

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
    @Query(value = "select * from employee e where e.id in (select infraction.id_employee from infraction  where infraction.date > current_date  - interval '1 month' group by infraction.id_employee HAVING COUNT(infraction.id_employee)>2)", nativeQuery = true)
    Page<Employee> checkForDismissal(Pageable pageable);

}
