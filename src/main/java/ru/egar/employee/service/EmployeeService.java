package ru.egar.employee.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.egar.document.repo.DocumentRepository;
import ru.egar.employee.dto.EmployeeDTO;
import ru.egar.employee.dto.EmployeeInfoDTO;
import ru.egar.employee.mapper.EmployeeInfoMapper;
import ru.egar.exception.NotFoundException;
import ru.egar.exception.PersonnelNumberAlreadyExistException;
import ru.egar.employee.mapper.EmployeeMapper;
import ru.egar.employee.model.Employee;
import ru.egar.employee.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper mapper;
    private final EmployeeInfoMapper employeeInfoMapper;

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(EmployeeDTO employeeDTO) throws PersonnelNumberAlreadyExistException {
        if(employeeRepository.findByPersonnelNumber(employeeDTO.getPersonnelNumber()).isPresent()){
            throw  new PersonnelNumberAlreadyExistException("Такой табельный номер уже существует");
        }
        Employee employee = mapper.toEmployee(employeeDTO);
        employeeRepository.save(employee);
        log.info("Сотрудник: {}", employee.toString());
    }

    public Employee getEmployeeId(Long id) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()){
            throw new NotFoundException("Сотрудник с id="+id+" не найден");
        }
        return employee.get();
    }

    public Employee getPersonnelNumber(Integer personnelNumber) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findByPersonnelNumber(personnelNumber);
        if(employee.isEmpty()){
            throw new NotFoundException("Сотрудник с id="+personnelNumber+" не найден");
        }
        return employee.get();
    }


    public Page<EmployeeInfoDTO> getPageEmployee(String string, String field, String direction, int page){
        int size = 5;
        if(string != null &&string.equals("")){
            string = null;
        }
        Pageable pageable = PageRequest.of(page, size, direction.equals("asc")? Sort.by(field).ascending(): Sort.by(field).descending());
        return employeeRepository.listEmployee(string, pageable).map(employeeInfoMapper::toEmployeeInfo);

    }

    public Page<EmployeeInfoDTO> getListEmployee(String position, String department, String field, String direction, int page){
        int size =5;
        Pageable pageable = PageRequest.of(page, size, direction.equals("asc")? Sort.by(field).ascending(): Sort.by(field).descending());
        if(position != null && position.equals("")){
            position = null;
        }

        if(department != null && department.equals("")){
            department = null;
        }

        if(position==null && department==null){
            return employeeRepository.listNotEmployee(pageable).map(employeeInfoMapper::toEmployeeInfo);
        }else{
            return employeeRepository.listEmployee(position, department, pageable).map(employeeInfoMapper::toEmployeeInfo);
        }

    }

    public Page<EmployeeInfoDTO> getEmployeesOnDismissal(String field, String direction, int page){
        int size = 5;
        Pageable pageable = PageRequest.of(page,size, direction.equals("asc")? Sort.by(field).ascending(): Sort.by(field).descending());
        return employeeRepository.checkForDismissal(pageable).map(employeeInfoMapper::toEmployeeInfo);
    }

    public boolean getCheckGoodCertification(Integer personnelNumber){
        return employeeRepository.checkGoodCertification(personnelNumber);
    }

    public boolean getCheckInfractions(Integer personnelNumber){
        return employeeRepository.checkInfractions(personnelNumber);
    }
    public boolean getCheckLastEducationAndCertification(Integer personnelNumber){
        return employeeRepository.checkLastEducationAndCertification(personnelNumber);
    }

}
