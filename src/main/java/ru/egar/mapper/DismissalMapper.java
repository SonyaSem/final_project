package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.document.DismissalDTO;
import ru.egar.model.Dismissal;

import ru.egar.reporitory.ReasonRepository;

@Mapper
public abstract class DismissalMapper {

    @Autowired
    protected ReasonRepository reasonRepository;

    public Dismissal toDismissal (DismissalDTO dismissalDTO){
        if (dismissalDTO == null ) {
            return null;
        }

        Dismissal dismissal = new Dismissal();
        dismissal.setDescription(dismissalDTO.getDescription());
        dismissal.setIdReason(reasonRepository.findById(dismissalDTO.getReason()).get());
        return dismissal;
    }
}
