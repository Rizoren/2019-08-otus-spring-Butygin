package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository {
    Optional<Books> findById(long id);
    List<Books> findAll();
    List<Books> findAllByAuthor(Authors author);
    List<Books> findAllByGenre(Genres genres);
    Books save(Books books);
    void delete(Books books);
}
