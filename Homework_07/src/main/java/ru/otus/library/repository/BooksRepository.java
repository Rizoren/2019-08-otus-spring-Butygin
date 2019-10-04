package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>, BooksRepositoryCustom {
    List<Books> findAllByAuthors(Authors authors);
    List<Books> findAllByGenres(Genres genres);
}
