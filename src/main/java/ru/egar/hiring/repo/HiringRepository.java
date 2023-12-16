package ru.egar.hiring.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egar.hiring.model.Hiring;
import ru.egar.reporitory.ReadRepository;

import java.util.Optional;

@Repository
public interface HiringRepository extends ReadRepository<Hiring, Long> {

    @Query(value = "select * from hiring where hiring.id_document =?1", nativeQuery = true)
    Optional <Hiring> findByIdDocument(Long id);

}
