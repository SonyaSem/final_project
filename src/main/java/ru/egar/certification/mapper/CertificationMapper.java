package ru.egar.certification.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.egar.certification.dto.CertificationDTO;

import ru.egar.certification.dto.CertificationInfoDTO;
import ru.egar.certification.dto.EmployeeCertificationDTO;
import ru.egar.certification.model.Certification;
import ru.egar.certification.repo.CertificationRepository;
import ru.egar.employee.model.Employee;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CertificationMapper {

    @Mapping(target = "idEmployee", source = "employee" )
    @Mapping(target = "id", source = "certificationDTO.id" )
    public Certification toCertification (CertificationDTO certificationDTO, Employee employee);

    @Mapping(target = "personnelNumber", source = "certification.idEmployee.personnelNumber")
    @Mapping(target = "firstName", source = "certification.idEmployee.firstName")
    @Mapping(target = "secondName", source = "certification.idEmployee.secondName")
    @Mapping(target = "lastName", source = "certification.idEmployee.lastName")
    @Mapping(target = "grade", source = "certification.grade.result")
    public CertificationInfoDTO toCertificationInfo(Certification certification);

    @Mapping(target = "personnelNumber", source = "employee.personnelNumber")
    @Mapping(target = "firstName", source = "employee.firstName")
    @Mapping(target = "secondName", source = "employee.secondName")
    @Mapping(target = "lastName", source = "employee.lastName")
    public EmployeeCertificationDTO toEmployeeCertification(CertificationRepository.EmployeeCertification employeeCertification, Employee employee);

}
