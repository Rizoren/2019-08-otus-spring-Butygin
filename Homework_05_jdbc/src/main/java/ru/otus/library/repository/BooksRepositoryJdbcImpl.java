package ru.otus.library.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Books;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BooksRepositoryJdbcImpl implements BooksRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BooksRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Books> findById(long id) {
        return null;
    }

    @Override
    public List<Books> findAll() {
        return null;
    }

    @Override
    public Books save(Books books) {
        return null;
    }

    @Override
    public void delete(Books books) {

    }
}
