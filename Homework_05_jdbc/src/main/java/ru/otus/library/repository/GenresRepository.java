package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genres;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenresRepository {
    Optional<Genres> findById(long id);
    List<Genres> findAll();
    Genres save(Genres genres);
    void delete(Genres genres);
}
