package ru.egar.education.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.egar.education.dto.EducationDTO;
import ru.egar.education.model.Education;
import ru.egar.employee.model.Employee;
import ru.egar.speciality.model.Speciality;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EducationMapper {

    @Mapping(target = "idEmployee", source = "employee" )
    @Mapping(target = "idSpeciality", source = "speciality")
    @Mapping(target = "name", source = "educationDTO.name")
    @Mapping(target = "id", source = "educationDTO.id")
    public Education toEducation(EducationDTO educationDTO, Employee employee, Speciality speciality) ;

}
