package ru.otus.library.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.model.Authors;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorsRepositoryJdbcImpl implements AuthorsRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorsRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Authors> findById(long id) {
        return null;
    }

    @Override
    public List<Authors> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Authors save(Authors authors) {
        return null;
    }

    @Override
    public void delete(Authors authors) {

    }
}
