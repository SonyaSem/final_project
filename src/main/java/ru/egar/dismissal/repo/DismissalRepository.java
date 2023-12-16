package ru.egar.dismissal.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egar.dismissal.model.Dismissal;
import ru.egar.reporitory.ReadRepository;

import java.util.Optional;

@Repository
public interface DismissalRepository extends ReadRepository<Dismissal,Long > {

    @Query(value = "select * from dismissal where dismissal.id_document =?1", nativeQuery = true)
    Optional<Dismissal> findByIdDocument(Long id);

}
