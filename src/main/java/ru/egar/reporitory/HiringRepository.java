package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egar.model.Hiring;

import java.util.Optional;

@Repository
public interface HiringRepository extends ReadRepository<Hiring, Long> {

    @Query(value = "select * from hiring where hiring.id_document =?1", nativeQuery = true)
    Hiring findByIdDocument(Long id);

}
