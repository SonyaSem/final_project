package ru.egar.infraction.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.egar.certification.dto.CertificationInfoDTO;
import ru.egar.certification.dto.EmployeeCertificationDTO;
import ru.egar.infraction.dto.EmployeeInfractionDTO;
import ru.egar.infraction.dto.InfractionDTO;
import ru.egar.infraction.dto.InfractionInfoDTO;
import ru.egar.infraction.dto.InfractionMonth;
import ru.egar.employee.model.Employee;
import ru.egar.employee.repo.EmployeeRepository;
import ru.egar.infraction.mapper.InfractionMapper;
import ru.egar.infraction.model.Infraction;
import ru.egar.infraction.repo.InfractionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfractionService {
    private final InfractionRepository infractionRepository;
    private final EmployeeRepository employeeRepository;
    private final InfractionMapper mapper;

    public List<Infraction> listInfraction() {
        return infractionRepository.findAll();
    }

    public void saveInfraction(InfractionDTO infractionDTO){
        Employee employee = employeeRepository.findByPersonnelNumber(infractionDTO.getEmployee()).get();
        Infraction infraction = mapper.toInfraction(infractionDTO, employee);
        infractionRepository.save(infraction);
        log.info("Нарушение: {}", infraction.toString());
    }

    public List<InfractionMonth> getStaticInfraction(Integer year){
        return infractionRepository.listInfractionMonth(year);
    }

    public List<Infraction> getListEmployeeInfraction(Integer number){
        return infractionRepository.listEmployeeInfraction(number);
    }

    public List<InfractionInfoDTO> getLisInfraction(){
        List<InfractionInfoDTO> list  = infractionRepository.listInfraction().stream().map(mapper::toInfractionInfo).collect(Collectors.toList());
        return list;
    }

    public List<EmployeeInfractionDTO> getInfractionStatistic(Integer year){

        List<EmployeeInfractionDTO> list = infractionRepository.listInfractionEmployee(year).stream().map(c->mapper.toEmployeeInfraction(c, employeeRepository.findById(c.getEmployee()).get())).collect(Collectors.toList());
        return list;
    }

}
