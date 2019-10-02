package ru.otus.library.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import java.util.List;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class LibraryRepositoryJdbcImpl implements LibraryRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public LibraryRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Authors> findAllAuthors() {
        return null;
    }

    @Override
    public List<Genres> findAllGenres() {
        return null;
    }

    @Override
    public List<Books> findAllBooks() {
        return null;
    }

    @Override
    public List<Books> findAllByAuthorID(long id) {
        return null;
    }

    @Override
    public List<Books> findAllByGenreID(long id) {
        return null;
    }

    @Override
    public Books findRandomBook() {
        return null;
    }
}

