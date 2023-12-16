package ru.egar.education.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.education.dto.EducationDTO;
import ru.egar.education.mapper.EducationMapper;
import ru.egar.education.model.Education;
import ru.egar.education.repo.EducationRepository;
import ru.egar.employee.model.Employee;
import ru.egar.employee.repo.EmployeeRepository;
import ru.egar.speciality.model.Speciality;
import ru.egar.speciality.repo.SpecialityRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EmployeeRepository employeeRepository;
    private final SpecialityRepository specialityRepository;
    private final EducationMapper educationMapper;

    public void saveEducation(EducationDTO educationDTO){
        Employee employee = employeeRepository.findByPersonnelNumber(educationDTO.getEmployee()).get();
        Speciality speciality = specialityRepository.findById(educationDTO.getSpeciality()).get();
        Education education = educationMapper.toEducation(educationDTO, employee, speciality);
        educationRepository.save(education);
        log.info("Образование:  {}", education.toString());
    }

    public List<Education> getListEmployeeEducation(Integer number){
        return educationRepository.listEmployeeEducation(number);
    }
}
