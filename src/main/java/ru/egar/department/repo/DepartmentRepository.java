package ru.egar.department.repo;

import org.springframework.stereotype.Repository;
import ru.egar.department.model.Department;
import ru.egar.reporitory.ReadRepository;

@Repository
public interface DepartmentRepository extends ReadRepository<Department, Long> {
}
