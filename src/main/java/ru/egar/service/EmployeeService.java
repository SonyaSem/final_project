package ru.egar.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import ru.egar.dto.EmployeeDTO;
import ru.egar.exception.NotFoundException;
import ru.egar.exception.PersonnelNumberAlreadyExistException;
import ru.egar.mapper.EmployeeMapper;
import ru.egar.model.Employee;
import ru.egar.reporitory.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

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

    public List<EmployeeRepository.CertificationInfoEmployee> getCertificationStatistic(Integer year){
        return employeeRepository.listCertificationEmployee(year);
    }

    public List<EmployeeRepository.InfractionEmployee> getInfractionStatistic(Integer year){
        return employeeRepository.listInfractionEmployee(year);
    }

    public List<EmployeeRepository.EmployeeInfoDTO> getListEmployee(Integer number, String lastName, String firstName, String secondName,
                                                                String phone){
        if(number == null && lastName==null && firstName==null && secondName==null && phone==null){
            return employeeRepository.listEmployee();
        }else {
            return employeeRepository.listEmployee(number, lastName, firstName, secondName, phone);
        }
    }

    public List<EmployeeRepository.EmployeeInfoDTO> getListEmployee(String position, String department){
            return employeeRepository.listEmployee(position, department);
    }

    public List<EmployeeRepository.EmployeeInfoDTO> getEmployeesOnDismissal(){
        return employeeRepository.checkForDismissal();
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
