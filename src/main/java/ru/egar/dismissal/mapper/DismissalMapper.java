package ru.egar.dismissal.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.egar.dismissal.dto.DismissalDTO;
import ru.egar.dismissal.model.Dismissal;

import ru.egar.reason.model.Reason;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DismissalMapper {

    @Mapping(target = "idReason", source = "reason" )
    @Mapping(target = "id", source = "dismissalDTO.id")
    public Dismissal toDismissal (DismissalDTO dismissalDTO, Reason reason);
}
