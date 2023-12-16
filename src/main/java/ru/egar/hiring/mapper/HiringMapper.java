package ru.egar.hiring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.egar.department.model.Department;
import ru.egar.hiring.dto.HiringDTO;
import ru.egar.hiring.model.Hiring;

import ru.egar.position.model.Position;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HiringMapper {

    @Mapping(target = "idPosition", source = "position" )
    @Mapping(target = "idDepartment", source = "department" )
    @Mapping(target = "id", source = "hiringDTO.id")
    public Hiring toHiring (HiringDTO hiringDTO, Position position, Department department);

}
