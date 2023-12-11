package ru.egar.reporitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import ru.egar.model.Position;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadRepository<T, ID> extends JpaRepository<T, ID> {

     Optional <T> findById(ID id);
    List<T> findAll();
}
