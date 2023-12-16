package ru.egar.reason.repo;

import org.springframework.stereotype.Repository;
import ru.egar.reason.model.Reason;
import ru.egar.reporitory.ReadRepository;

@Repository
public interface ReasonRepository extends ReadRepository<Reason, Long> {

}
