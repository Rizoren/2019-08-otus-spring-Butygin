package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorsRepository {
    Optional<Authors> findById(long id);
    List<Authors> findAll();
    Authors save(Authors author);
    void delete(Authors author);
}
