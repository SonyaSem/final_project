package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.document.DocumentDTO;
import ru.egar.exception.NotFoundException;
import ru.egar.model.Document;

import ru.egar.service.EmployeeService;

@Mapper
public abstract class DocumentMapper {

    @Autowired
    protected EmployeeService employeeService;

    public Document toDocument  (DocumentDTO documentDTO) {
        if ( documentDTO == null ) {
            return null;
        }

        Document document = new Document();
        document.setNumber(documentDTO.getNumber());
        document.setName(documentDTO.getName());
        document.setType(documentDTO.getType());
        document.setCreateDate(documentDTO.getCreateDate());
        document.setStartDate(documentDTO.getStartDate());
        document.setIdEmployee(employeeService.getPersonnelNumber(documentDTO.getPersonnelNumber()));

        return document;
    }
}
