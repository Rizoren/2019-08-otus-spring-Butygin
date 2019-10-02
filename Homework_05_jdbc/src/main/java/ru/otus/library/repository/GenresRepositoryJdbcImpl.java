package ru.otus.library.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genres;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenresRepositoryJdbcImpl implements GenresRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenresRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Genres> findById(long id) {
        return null;
    }

    @Override
    public List<Genres> findAll() {
        return null;
    }

    @Override
    public Genres save(Genres genres) {
        return null;
    }

    @Override
    public void delete(Genres  genres) {

    }
}
