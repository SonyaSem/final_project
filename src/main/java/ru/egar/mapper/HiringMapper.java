package ru.egar.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egar.dto.document.HiringDTO;
import ru.egar.model.Hiring;
import ru.egar.reporitory.DepartmentRepository;

import ru.egar.reporitory.PositionRepository;

@Mapper
public abstract class HiringMapper {

    @Autowired
    protected PositionRepository positionRepository;

    @Autowired
    protected DepartmentRepository departmentRepository;

    public Hiring toHiring (HiringDTO hiringDTO){
        if ( hiringDTO == null ) {
            return null;
        }

        Hiring hiring = new Hiring();
        hiring.setIdPosition(positionRepository.findById(hiringDTO.getPosition()).get());
        hiring.setIdDepartment(departmentRepository.findById(hiringDTO.getDepartment()).get());
        return hiring;
    }

}
