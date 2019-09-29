package ru.otus.library.repository;

import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import java.util.List;

public interface LibraryRepository {

    List<Authors> findAllAuthors();
    List<Genres> findAllGenres();
    List<Books> findAllBooks();
    List<Books> findAllByAuthorID(long id);
    List<Books> findAllByGenreID(long id);
}
