package ru.egar.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.egar.employee.dto.EmployeeDTO;
import ru.egar.employee.model.Employee;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    public Employee toEmployee(EmployeeDTO employeeDTO);

}
