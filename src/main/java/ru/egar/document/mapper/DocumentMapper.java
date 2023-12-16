package ru.egar.document.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.egar.document.dto.DocumentDTO;
import ru.egar.document.model.Document;

import ru.egar.employee.model.Employee;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentMapper {
    @Mapping(target = "idEmployee", source = "employee" )
    @Mapping(target = "id", source = "documentDTO.id")
    public Document toDocument  (DocumentDTO documentDTO, Employee employee);
}
