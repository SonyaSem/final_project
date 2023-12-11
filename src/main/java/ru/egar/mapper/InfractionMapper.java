package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.InfractionDTO;

import ru.egar.model.Infraction;
import ru.egar.reporitory.EmployeeRepository;
import ru.egar.service.EmployeeService;

@Mapper
public abstract class InfractionMapper {

    @Autowired
    protected EmployeeService employeeService;

    public Infraction toInfraction (InfractionDTO infractionDTO){
        Infraction infraction = new Infraction();
        infraction.setDescription(infractionDTO.getDescription());
        infraction.setDate(infractionDTO.getDate());
        infraction.setPunishment(infractionDTO.getPunishment());
        infraction.setIdEmployee(employeeService.getPersonnelNumber(infractionDTO.getEmployee()));

        return infraction;
    }
}
