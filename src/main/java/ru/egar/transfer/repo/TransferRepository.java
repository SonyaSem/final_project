package ru.egar.transfer.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.egar.reporitory.ReadRepository;
import ru.egar.transfer.model.Transfer;

import java.util.Optional;

@Repository
public interface TransferRepository extends ReadRepository<Transfer, Long> {

    @Query(value = "select * from transfer where transfer.id_document =?1", nativeQuery = true)
    Optional <Transfer> findByIdDocument(Long id);
}
