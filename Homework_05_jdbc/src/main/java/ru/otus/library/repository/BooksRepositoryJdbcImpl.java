package ru.otus.library.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Books;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BooksRepositoryJdbcImpl implements BooksRepository {

    //WARNING! I use RETURNING... lifehack for save() and work with obj
    final String INSERT_QUERY = "insert into books (book_name, book_description, book_year, book_isbn) values (:book_name, :book_description, :book_year, :book_isbn) RETURNING books.*";
    final String UPDATE_QUERY = "update books set book_name = :book_name, book_description = :book_description, book_year = :book_year, book_isbn = :book_isbn where book_id = :book_id RETURNING books.*";
    final String DELETE_QUERY = "delete from books where book_id = :book_id";

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BooksRepositoryJdbcImpl(NamedParameterJdbcOperations namedParameterJdbcOperations)
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<Books> findById(long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return Optional.ofNullable(namedParameterJdbcOperations
                .queryForObject("select * from books where book_id = :id", namedParameters, new JDBCRowMapper() ));
    }

    @Override
    public List<Books> findAll() {
        return namedParameterJdbcOperations.query("select * from books", new JDBCRowMapper() );
    }

    @Override
    public Books save(Books books) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("book_id", books.getBook_id())
                .addValue("book_name", books.getBook_name())
                .addValue("book_description", books.getBook_description())
                .addValue("book_year", books.getBook_year())
                .addValue("book_isbn", books.getBook_isbn());

        if (books.getBook_id() <= 0)
        {
            return namedParameterJdbcOperations.queryForObject(INSERT_QUERY, namedParameters, new JDBCRowMapper());
        }
        else
        {
            return namedParameterJdbcOperations.queryForObject(UPDATE_QUERY, namedParameters, new JDBCRowMapper());
        }
    }

    @Override
    public void delete(Books books) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("book_id", books.getBook_id());
        namedParameterJdbcOperations.update("delete from books_authors where book_id = :book_id", namedParameters );
        namedParameterJdbcOperations.update("delete from books_genres where book_id = :book_id", namedParameters );
        namedParameterJdbcOperations.update(DELETE_QUERY, namedParameters );
    }

    private static class JDBCRowMapper implements RowMapper<Books> {
        @Override
        public Books mapRow(ResultSet resultSet, int i) throws SQLException {
            long book_id = resultSet.getLong("book_id");
            String book_name = resultSet.getString("book_name");
            String book_description = resultSet.getString("book_description");
            Integer book_year = resultSet.getInt("book_year");
            String book_isbn = resultSet.getString("book_isbn");
            return new Books(book_id, book_name, book_description, book_year, book_isbn, null, null);
        }
    }

    @Override
    public void addAuthor(Books books, long author_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("book_id", books.getBook_id())
                .addValue("author_id", author_id);
        namedParameterJdbcOperations.update("insert into books_authors(book_id, author_id) values (:book_id, :author_id)", namedParameters );
    }
    @Override
    public void addGenre(Books books, long genre_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("book_id", books.getBook_id())
                .addValue("genre_id", genre_id);
        namedParameterJdbcOperations.update("insert into books_genres(book_id, genre_id) values (:book_id, :genre_id)", namedParameters );
    }

    @Override
    public List<Books> findAllByAuthorID(long author_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("author_id", author_id);
        return namedParameterJdbcOperations.query("select * from books b, books_authors ba where b.book_id = ba.book_id and ba.author_id = :author_id ", namedParameters, new JDBCRowMapper() );
    }
    @Override
    public List<Books> findAllByGenreID(long genre_id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("genre_id", genre_id);
        return namedParameterJdbcOperations.query("select * from books b, books_genres bg where b.book_id = bg.book_id and bg.genre_id = :genre_id ", namedParameters, new JDBCRowMapper() );
    }
}
