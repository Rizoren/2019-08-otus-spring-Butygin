package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Books;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository {
    Optional<Books> findById(long id);
    List<Books> findAll();
    Books save(Books books);
    void delete(Books books);

    void addAuthor(Books books, long author_id);
    void addGenre(Books books, long genre_id);

    List<Books> findAllByAuthorID(long author_id);
    List<Books> findAllByGenreID(long genre_id);
}
