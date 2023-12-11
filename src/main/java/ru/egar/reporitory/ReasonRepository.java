package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egar.model.Reason;

@Repository
public interface ReasonRepository extends ReadRepository<Reason, Long> {

}
