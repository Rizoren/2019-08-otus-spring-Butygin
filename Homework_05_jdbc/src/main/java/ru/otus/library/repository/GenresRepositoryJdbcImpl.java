package ru.otus.library.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenresRepositoryJdbcImpl implements GenresRepository {

    //WARNING! I use RETURNING... lifehack for save() and work with obj
    final String INSERT_QUERY = "insert into genres (genre_name) values (:genre_name) RETURNING genres.*";
    final String UPDATE_QUERY = "update genres set genre_name = :genre_name where genre_id = :genre_id RETURNING genres.*";
    final String DELETE_QUERY = "delete from genres where genre_id = :genre_id";

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenresRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Genres> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return Optional.ofNullable(namedParameterJdbcOperations
                .queryForObject("select * from genres where genre_id = :id", namedParameters, new JDBCRowMapper() ));
    }

    @Override
    public List<Genres> findAll() {
        return namedParameterJdbcOperations.query("select * from genres", new JDBCRowMapper() );
    }

    @Override
    public Genres save(Genres genres) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("genre_id", genres.getGenre_id())
                .addValue("genre_name", genres.getGenre_name());

        if (genres.getGenre_id() <= 0)
        {
            return namedParameterJdbcOperations.queryForObject(INSERT_QUERY, namedParameters, new JDBCRowMapper());
        }
        else
        {
            return namedParameterJdbcOperations.queryForObject(UPDATE_QUERY, namedParameters, new JDBCRowMapper());
        }
    }

    @Override
    public void delete(Genres  genres) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("genre_id", genres.getGenre_id());
        namedParameterJdbcOperations.update(DELETE_QUERY, namedParameters );
    }

    private static class JDBCRowMapper implements RowMapper<Genres> {
        @Override
        public Genres mapRow(ResultSet resultSet, int i) throws SQLException {
            long genre_id = resultSet.getLong("genre_id");
            String genre_name = resultSet.getString("genre_name");
            return new Genres(genre_id,genre_name);
        }
    }
}
