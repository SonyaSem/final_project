package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.document.TransferDTO;
import ru.egar.exception.NotFoundException;
import ru.egar.model.Transfer;
import ru.egar.reporitory.DepartmentRepository;
import ru.egar.reporitory.PositionRepository;
import ru.egar.reporitory.ReasonRepository;

@Mapper
public abstract class TransferMapper {
    @Autowired
    protected  PositionRepository positionRepository;

    @Autowired
    protected DepartmentRepository departmentRepository;

    @Autowired
    protected ReasonRepository reasonRepository;

    public Transfer toTransfer (TransferDTO transferDTO){
        if ( transferDTO == null ) {
            return null;
        }

        Transfer transfer = new Transfer();
        transfer.setIdPosition(positionRepository.findById(transferDTO.getPosition()).orElseThrow(()->new NotFoundException("Должность не найдена")));
        transfer.setIdDepartment(departmentRepository.findById(transferDTO.getDepartment()).orElseThrow(()->new NotFoundException("Отдел не найден")));
        transfer.setIdReason(reasonRepository.findById(transferDTO.getReason()).orElseThrow(()->new NotFoundException("Принина не найдена")));
        return transfer;
    }
}
