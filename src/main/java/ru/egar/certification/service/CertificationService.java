package ru.egar.certification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.certification.dto.CertificationDTO;
import ru.egar.certification.dto.CertificationInfoDTO;
import ru.egar.certification.dto.EmployeeCertificationDTO;
import ru.egar.certification.mapper.CertificationMapper;

import ru.egar.certification.model.Certification;
import ru.egar.certification.repo.CertificationRepository;
import ru.egar.document.dto.RegisterDTO;
import ru.egar.employee.model.Employee;
import ru.egar.employee.repo.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final EmployeeRepository employeeRepository;
    private final CertificationMapper mapper;

    public void saveCertification(CertificationDTO certificationDTO){
        Employee employee = employeeRepository.findByPersonnelNumber(certificationDTO.getEmployeeNumber()).get();

        Certification certification = mapper.toCertification(certificationDTO, employee);
        certificationRepository.save(certification);
        log.info("Аттестация: {}", certification.toString());
    }

    // статсистика по оценкам
    public List<CertificationRepository.CertificationGarde> getListStatisticGrade(Integer year){
        return certificationRepository.listCertificationGrade(year);
    }

    // все аттестации
    public List<CertificationInfoDTO> getListCertification(){
        List<CertificationInfoDTO> list  = certificationRepository.listCertification().stream().map(mapper::toCertificationInfo).collect(Collectors.toList());
        return list;
    }

    // аттестации сотрудника
    public List<Certification> getEmployeeCertification(Integer number){
        return certificationRepository.listEmployeeCertification(number);
    }

    public List<EmployeeCertificationDTO> getCertificationStatistic(Integer year){

        List<EmployeeCertificationDTO> list = certificationRepository.listCertificationEmployee(year).stream().map(c->mapper.toEmployeeCertification(c, employeeRepository.findById(c.getEmployee()).get())).collect(Collectors.toList());
        return list;
    }


}
