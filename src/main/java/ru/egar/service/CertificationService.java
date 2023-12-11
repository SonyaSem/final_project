package ru.egar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.dto.CertificationDTO;
import ru.egar.mapper.CertificationMapper;

import ru.egar.model.Certification;
import ru.egar.reporitory.CertificationRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final CertificationMapper mapper;

    public void saveCertification(CertificationDTO certificationDTO){
        Certification certification = mapper.toCertification(certificationDTO);
        certificationRepository.save(certification);
        log.info("Аттестация: {}", certification.toString());
    }

    // статсистика по оценкам
    public List<CertificationRepository.CertificationGarde> getListStatisticGrade(Integer year){
        return certificationRepository.listCertificationGrade(year);
    }

    // все аттестации
    public List<CertificationRepository.CertificationInfoDTO> getListCertification(){
        return certificationRepository.listCertification();
    }

    // аттестации сотрудника
    public List<CertificationRepository.EmployeeCertificationDTO> getEmployeeCertification(Integer number){
        return certificationRepository.listEmployeeCertification(number);
    }


}
