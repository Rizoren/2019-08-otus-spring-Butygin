package ru.otus.library.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.model.Authors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorsRepositoryJdbcImpl implements AuthorsRepository {

    //WARNING! I use RETURNING... lifehack for save() and work with obj
    final String INSERT_QUERY = "insert into authors (author_family, author_name, author_patronymic) values (:author_family, :author_name, :author_patronymic) RETURNING authors.*";
    final String UPDATE_QUERY = "update authors set author_family = :author_family, author_name = :author_name, author_patronymic = :author_patronymic where author_id = :author_id RETURNING authors.*";
    final String DELETE_QUERY = "delete from authors where author_id = :author_id";

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorsRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Authors> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return Optional.ofNullable(namedParameterJdbcOperations
                .queryForObject("select * from authors where author_id = :id", namedParameters, new JDBCRowMapper() ));
    }

    @Override
    public List<Authors> findAll() {
        return namedParameterJdbcOperations.query("select * from authors", new JDBCRowMapper() );
    }

    @Override
    @Transactional
    public Authors save(Authors authors) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("author_id", authors.getAuthor_id())
                .addValue("author_family", authors.getAuthor_family())
                .addValue("author_name", authors.getAuthor_family())
                .addValue("author_patronymic", authors.getAuthor_family());

        if (authors.getAuthor_id() <= 0)
        {
            return namedParameterJdbcOperations.queryForObject(INSERT_QUERY, namedParameters, new JDBCRowMapper());
        }
        else
        {
            return namedParameterJdbcOperations.queryForObject(UPDATE_QUERY, namedParameters, new JDBCRowMapper());
        }
    }

    @Override
    public void delete(Authors authors) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("author_id", authors.getAuthor_id());
        namedParameterJdbcOperations.update(DELETE_QUERY, namedParameters );
    }

    private static class JDBCRowMapper implements RowMapper<Authors> {
        @Override
        public Authors mapRow(ResultSet resultSet, int i) throws SQLException {
            long author_id = resultSet.getLong("author_id");
            String author_family = resultSet.getString("author_family");
            String author_name = resultSet.getString("author_name");
            String author_patronymic = resultSet.getString("author_patronymic");
            return new Authors(author_id,author_family,author_name,author_patronymic);
        }
    }
}
