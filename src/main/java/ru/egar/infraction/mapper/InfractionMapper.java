package ru.egar.infraction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.egar.certification.dto.EmployeeCertificationDTO;
import ru.egar.certification.model.Certification;
import ru.egar.certification.repo.CertificationRepository;
import ru.egar.infraction.dto.EmployeeInfractionDTO;
import ru.egar.infraction.dto.InfractionDTO;

import ru.egar.employee.model.Employee;
import ru.egar.infraction.dto.InfractionInfoDTO;
import ru.egar.infraction.model.Infraction;
import ru.egar.infraction.repo.InfractionRepository;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InfractionMapper {

    @Mapping(target = "idEmployee", source = "employee" )
    @Mapping(target = "id", source = "infractionDTO.id")
    public Infraction toInfraction(InfractionDTO infractionDTO, Employee employee);


    @Mapping(target = "personnelNumber", source = "infraction.idEmployee.personnelNumber")
    @Mapping(target = "firstName", source = "infraction.idEmployee.firstName")
    @Mapping(target = "secondName", source = "infraction.idEmployee.secondName")
    @Mapping(target = "lastName", source = "infraction.idEmployee.lastName")
    public InfractionInfoDTO toInfractionInfo(Infraction infraction);

    @Mapping(target = "personnelNumber", source = "employee.personnelNumber")
    @Mapping(target = "firstName", source = "employee.firstName")
    @Mapping(target = "secondName", source = "employee.secondName")
    @Mapping(target = "lastName", source = "employee.lastName")
    public EmployeeInfractionDTO toEmployeeInfraction(InfractionRepository.InfractionEmployee infractionEmployee, Employee employee);
}
