package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egar.model.Speciality;

@Repository
public interface SpecialityRepository extends ReadRepository<Speciality, Long> {

}
