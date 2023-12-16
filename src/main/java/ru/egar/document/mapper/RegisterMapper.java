package ru.egar.document.mapper;

import org.mapstruct.Mapper;
import ru.egar.document.model.Document;
import ru.egar.document.dto.RegisterDTO;
import ru.egar.employee.model.Employee;
import ru.egar.enums.DocumentType;


//вывод документов всех видов
@Mapper
public abstract class RegisterMapper {

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
            registry.setDepartment(document.getHiring().getIdDepartment().getName());
            registry.setPosition(document.getHiring().getIdPosition().getName());

        }else if(document.getType().equals(DocumentType.TRANSFER)){
            registry.setPosition(document.getTransfer().getIdPosition().getName());
            registry.setDepartment(document.getTransfer().getIdDepartment().getName());
            registry.setReason(document.getTransfer().getIdReason().getName());

        }else if(document.getType().equals(DocumentType.DISMISSAL)){
            registry.setReason(document.getDismissal().getIdReason().getName());
            registry.setDescription(document.getDismissal().getDescription());
        }
        return registry;

    }

}
