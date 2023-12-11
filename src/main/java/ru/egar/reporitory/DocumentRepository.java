package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.egar.enums.DocumentType;
import ru.egar.model.Document;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByType(DocumentType type);

    @Query(value = "select * from document where id_employee = (select e.id from employee e where e.personnel_number=:number) " +
            "order by create_date desc", nativeQuery = true)
    List<Document> findByPersonnelNumber(@Param("number") Integer number);

}
