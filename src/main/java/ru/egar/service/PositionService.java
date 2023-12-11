package ru.egar.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.egar.exception.NotFoundException;
import ru.egar.model.Position;
import ru.egar.reporitory.PositionRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public List<Position> listPosition() {
        return positionRepository.findAll();
    }

    public Position getPositionId(Long id){
        Optional<Position> position = positionRepository.findById(id);
        if(position.isEmpty()){
            throw new NotFoundException("Должность с id=" + id+"не найдена");
        }
        return position.get();
    }

    public List<Position> getSuitablePosition(Long id){
        return positionRepository.getPosition(id);
    }

    public List<Position> getSuitablePosition(Integer number){
        return positionRepository.getPosition(number);
    }

}
