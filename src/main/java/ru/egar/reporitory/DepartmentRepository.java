package ru.egar.reporitory;

import org.springframework.stereotype.Repository;
import ru.egar.model.Department;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends ReadRepository<Department, Long> {
}
