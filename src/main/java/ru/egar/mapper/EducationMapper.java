package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.EducationDTO;
import ru.egar.exception.NotFoundException;
import ru.egar.model.Education;
import ru.egar.reporitory.EmployeeRepository;
import ru.egar.reporitory.SpecialityRepository;
import ru.egar.service.EmployeeService;

@Mapper
public abstract class EducationMapper {

    @Autowired
    protected EmployeeService employeeService;

    @Autowired
    protected SpecialityRepository specialityRepository;

    public Education toEducation(EducationDTO educationDTO) {
        Education education = new Education();

        education.setName(educationDTO.getName());
        education.setUniversity(educationDTO.getUniversity());
        education.setFinishDate(educationDTO.getFinishDate());
        education.setIdSpeciality(specialityRepository.findById(educationDTO.getSpeciality()).get());
        education.setIdEmployee(employeeService.getPersonnelNumber(educationDTO.getEmployee()));

        return education;
    }

}
