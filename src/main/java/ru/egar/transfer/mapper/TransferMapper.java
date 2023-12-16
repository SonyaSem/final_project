package ru.egar.transfer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.egar.department.model.Department;
import ru.egar.transfer.dto.TransferDTO;
import ru.egar.position.model.Position;
import ru.egar.reason.model.Reason;
import ru.egar.transfer.model.Transfer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransferMapper {

    @Mapping(target = "idPosition", source = "position" )
    @Mapping(target = "idDepartment", source = "department" )
    @Mapping(target = "idReason", source = "reason" )
    @Mapping(target = "id", source = "transferDTO.id")
    public Transfer toTransfer (TransferDTO transferDTO, Position position, Department department, Reason reason);
}
