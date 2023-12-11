package ru.egar.mapper;

import org.mapstruct.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.CertificationDTO;

import ru.egar.model.Certification;
import ru.egar.reporitory.EmployeeRepository;
import ru.egar.service.EmployeeService;

@Mapper
public abstract class CertificationMapper {

    @Autowired
    protected EmployeeService employeeService;

    public Certification toCertification (CertificationDTO certificationDTO){

        if ( certificationDTO == null ) {
            return null;
        }

        Certification certification = new Certification();

        certification.setName(certificationDTO.getName());
        certification.setGrade(certificationDTO.getGrade());
        certification.setIdEmployee(employeeService.getPersonnelNumber(certificationDTO.getEmployee()));
        certification.setDate(certificationDTO.getDate());

        return certification;
    }

}
