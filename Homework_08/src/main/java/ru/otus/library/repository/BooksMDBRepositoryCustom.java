package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.GenresMDB;

import java.util.List;

@Repository
public interface BooksMDBRepositoryCustom {
    public List<GenresMDB> findDistinctGenresMDB();
}
