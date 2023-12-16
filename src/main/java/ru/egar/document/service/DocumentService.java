package ru.egar.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egar.department.model.Department;
import ru.egar.department.repo.DepartmentRepository;
import ru.egar.dismissal.dto.DismissalDTO;
import ru.egar.dismissal.mapper.DismissalMapper;
import ru.egar.document.dto.DocumentDTO;
import ru.egar.document.dto.RegisterDTO;
import ru.egar.document.mapper.DocumentMapper;
import ru.egar.document.mapper.RegisterMapper;
import ru.egar.employee.model.Employee;
import ru.egar.employee.repo.EmployeeRepository;
import ru.egar.hiring.dto.HiringDTO;
import ru.egar.hiring.mapper.HiringMapper;
import ru.egar.dismissal.model.Dismissal;
import ru.egar.document.model.Document;
import ru.egar.hiring.model.Hiring;
import ru.egar.position.model.Position;
import ru.egar.position.repo.PositionRepository;
import ru.egar.reason.model.Reason;
import ru.egar.reason.repo.ReasonRepository;
import ru.egar.transfer.dto.TransferDTO;
import ru.egar.transfer.mapper.TransferMapper;
import ru.egar.transfer.model.Transfer;
import ru.egar.dismissal.repo.DismissalRepository;
import ru.egar.document.repo.DocumentRepository;
import ru.egar.hiring.repo.HiringRepository;
import ru.egar.transfer.repo.TransferRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final HiringRepository hiringRepository;
    private final TransferRepository transferRepository;
    private final DismissalRepository dismissalRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ReasonRepository reasonRepository;

    private final DocumentMapper documentMapper;
    private final HiringMapper hiringMapper;
    private final TransferMapper transferMapper;
    private final DismissalMapper dismissalMapper;


    private final RegisterMapper mapper;

    public List<RegisterDTO> listDocument() {
        List<RegisterDTO> list = documentRepository.findAll().stream().map(mapper::toDocumentDTO).collect(Collectors.toList());
        return list;
    }

    public List<RegisterDTO> getListEmployeeDocument(Integer number){
        List<RegisterDTO> list  = documentRepository.findByPersonnelNumber(number).stream().map(mapper::toDocumentDTO).collect(Collectors.toList());
        return list;
    }

    @Transactional
    public void saveHiring(DocumentDTO documentDTO, HiringDTO hiringDTO) {

        Employee employee = employeeRepository.findByPersonnelNumber(documentDTO.getPersonnelNumber()).get();
        Document document = documentMapper.toDocument(documentDTO, employee);

        Position position = positionRepository.findById(hiringDTO.getPosition()).get();
        Department department = departmentRepository.findById(hiringDTO.getDepartment()).get();
        Hiring hiring = hiringMapper.toHiring(hiringDTO, position, department);

        hiring.setIdDocument(documentRepository.save(document));
        hiringRepository.save(hiring);
        log.info("Документ: {}", document.toString());
        log.info("Найм: {}", hiring.toString());
    }

    @Transactional
    public void saveTransfer(DocumentDTO documentDTO, TransferDTO transferDTO) {
        Employee employee = employeeRepository.findByPersonnelNumber(documentDTO.getPersonnelNumber()).get();
        Document document = documentMapper.toDocument(documentDTO, employee);

        Position position = positionRepository.findById(transferDTO.getPosition()).get();
        Department department = departmentRepository.findById(transferDTO.getDepartment()).get();
        Reason reason = reasonRepository.findById(transferDTO.getReason()).get();

        Transfer transfer = transferMapper.toTransfer(transferDTO, position, department, reason);
        transfer.setIdDocument(documentRepository.save(document));
        transferRepository.save(transfer);
        log.info("Документ: {}", document.toString());
        log.info("Перевод: {}", transfer.toString());

    }

    @Transactional
    public void saveDismissal(DocumentDTO documentDTO, DismissalDTO dismissalDTO) {
        Employee employee = employeeRepository.findByPersonnelNumber(documentDTO.getPersonnelNumber()).get();
        Document document = documentMapper.toDocument(documentDTO, employee);

        Reason reason = reasonRepository.findById(dismissalDTO.getReason()).get();
        Dismissal dismissal = dismissalMapper.toDismissal(dismissalDTO, reason);
        dismissal.setIdDocument(documentRepository.save(document));
        dismissalRepository.save(dismissal);
        log.info("Документ: {}", document.toString());
        log.info("Увольнение: {}",dismissal.toString());
    }

}
