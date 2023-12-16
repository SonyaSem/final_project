package ru.egar.speciality.repo;

import org.springframework.stereotype.Repository;
import ru.egar.speciality.model.Speciality;
import ru.egar.reporitory.ReadRepository;

@Repository
public interface SpecialityRepository extends ReadRepository<Speciality, Long> {

}
