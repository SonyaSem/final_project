package ru.egar.employee.mapper;

import org.mapstruct.Mapper;
import ru.egar.document.model.Document;
import ru.egar.employee.model.Employee;
import ru.egar.employee.dto.EmployeeInfoDTO;
import ru.egar.enums.DocumentType;

import java.util.Comparator;


@Mapper
public abstract class EmployeeInfoMapper {
    public EmployeeInfoDTO toEmployeeInfo(Employee employee){
        EmployeeInfoDTO employeeInfoDTO = new EmployeeInfoDTO();

        if(!employee.getDocuments().isEmpty()){
            Comparator<Document> documentComparator = Comparator
                    .comparing(Document::getCreateDate);

            Document document = employee.getDocuments().stream()
                    .max(documentComparator)
                    .get();
            if(document.getType() == DocumentType.HIRING){
                employeeInfoDTO.setPosition(document.getHiring().getIdPosition().getName());
                employeeInfoDTO.setSalary(document.getHiring().getIdPosition().getSalary());
                employeeInfoDTO.setDepartment(document.getHiring().getIdDepartment().getName());
            }else if(document.getType() == DocumentType.TRANSFER){
                employeeInfoDTO.setPosition(document.getTransfer().getIdPosition().getName());
                employeeInfoDTO.setSalary(document.getTransfer().getIdPosition().getSalary());
                employeeInfoDTO.setDepartment(document.getTransfer().getIdDepartment().getName());
            }
        }

        employeeInfoDTO.setPersonnelNumber(employee.getPersonnelNumber());
        employeeInfoDTO.setFirstName(employee.getFirstName());
        employeeInfoDTO.setSecondName(employee.getSecondName());
        employeeInfoDTO.setLastName(employee.getLastName());
        employeeInfoDTO.setBirthDate(employee.getBirthDate());
        employeeInfoDTO.setAddress(employee.getAddress());
        employeeInfoDTO.setPhone(employee.getPhone());

        return employeeInfoDTO;
    }

}
