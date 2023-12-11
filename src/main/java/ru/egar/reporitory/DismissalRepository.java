package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egar.model.Dismissal;

@Repository
public interface DismissalRepository extends ReadRepository <Dismissal,Long > {

    @Query(value = "select * from dismissal where dismissal.id_document =?1", nativeQuery = true)
    Dismissal findByIdDocument(Long id);

}
