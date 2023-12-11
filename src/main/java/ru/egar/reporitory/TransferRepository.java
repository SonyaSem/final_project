package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egar.model.Transfer;

@Repository
public interface TransferRepository extends ReadRepository<Transfer, Long> {

    @Query(value = "select * from transfer where transfer.id_document =?1", nativeQuery = true)
    Transfer findByIdDocument(Long id);
}
