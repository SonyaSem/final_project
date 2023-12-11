package ru.egar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import ru.egar.dto.InfractionDTO;
import ru.egar.dto.InfractionMonth;
import ru.egar.mapper.InfractionMapper;
import ru.egar.model.Infraction;
import ru.egar.reporitory.InfractionRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfractionService {
    private final InfractionRepository infractionRepository;
    public final InfractionMapper mapper;

    public List<Infraction> listInfraction() {
        return infractionRepository.findAll();
    }

    public void saveInfraction(InfractionDTO infractionDTO){
        Infraction infraction = mapper.toInfraction(infractionDTO);
        infractionRepository.save(infraction);
        log.info("Нарушение: {}", infraction.toString());
    }

    public List<InfractionMonth> getStaticInfraction(Integer year){
        return infractionRepository.listInfractionMonth(year);
    }

    public List<InfractionRepository.EmployeeInfractionDTO> getListEmployeeInfraction(Integer number){
        return infractionRepository.listEmployeeInfraction(number);
    }

    public List<InfractionRepository.InfractionInfoDTO> getLisInfraction(){
        return infractionRepository.listInfraction();
    }
}
