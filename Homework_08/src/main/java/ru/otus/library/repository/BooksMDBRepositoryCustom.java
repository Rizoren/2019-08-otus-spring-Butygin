package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Books;
import ru.otus.library.model.GenresMDB;

@Repository
public interface BooksMDBRepositoryCustom {

    //public Books findRandomBook();
    public GenresMDB findDistinctGenresMDB();
}
