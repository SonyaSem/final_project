package ru.egar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.dto.EducationDTO;
import ru.egar.mapper.EducationMapper;
import ru.egar.model.Certification;
import ru.egar.model.Education;
import ru.egar.reporitory.EducationRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;

    public void saveEducation(EducationDTO educationDTO){
        Education education = educationMapper.toEducation(educationDTO);
        educationRepository.save(education);
        log.info("Образование:  {}", education.toString());
    }

    public List<EducationRepository.EmployeeEducation> getListEmployeeEducation(Integer number){
        return educationRepository.listEmployeeEducation(number);
    }
}
