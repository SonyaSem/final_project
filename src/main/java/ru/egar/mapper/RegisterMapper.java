package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.document.RegisterDTO;
import ru.egar.enums.DocumentType;
import ru.egar.model.*;
import ru.egar.reporitory.*;


//вывод документов всех видов
@Mapper
public abstract class RegisterMapper {

    @Autowired
    protected HiringRepository hiringRepository;

    @Autowired
    protected TransferRepository transferRepository;

    @Autowired
    protected DismissalRepository dismissalRepository;

    public RegisterDTO toDocumentDTO(Document document){

        if ( document == null ) {
           return null;
        }

        RegisterDTO registry = new RegisterDTO();
        registry.setId(document.getId());
        registry.setName(document.getName());
        registry.setNumber(document.getNumber());
        registry.setStartDate(document.getStartDate());
        registry.setCreateDate(document.getCreateDate());
        registry.setType(document.getType().getType());

        Employee employee = document.getIdEmployee();
        registry.setPersonnelNumber(employee.getPersonnelNumber().toString());
        registry.setLastName(employee.getLastName());
        registry.setFirstName(employee.getFirstName());
        registry.setSecondName(employee.getSecondName());

        if(document.getType().equals(DocumentType.HIRING)){
            Hiring hiring = hiringRepository.findByIdDocument(document.getId());
            registry.setDepartment(hiring.getIdDepartment().getName());
            registry.setPosition(hiring.getIdPosition().getName());

        }else if(document.getType().equals(DocumentType.TRANSFER)){
            Transfer transfer = transferRepository.findByIdDocument(document.getId());
            registry.setPosition(transfer.getIdPosition().getName());
            registry.setDepartment(transfer.getIdDepartment().getName());
            registry.setReason(transfer.getIdReason().getName());

        }else if(document.getType().equals(DocumentType.DISMISSAL)){
            Dismissal dismissal = dismissalRepository.findByIdDocument(document.getId());
            registry.setReason(dismissal.getIdReason().getName());
            registry.setDescription(dismissal.getDescription());
        }
        return registry;

    }

}
