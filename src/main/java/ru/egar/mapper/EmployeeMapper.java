package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.egar.dto.EmployeeDTO;
import ru.egar.model.Employee;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    public Employee toEmployee(EmployeeDTO employeeDTO);

}
