package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genres;

@Repository
public interface GenresRepository extends JpaRepository<Genres, Long> {
}
