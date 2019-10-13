package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Books;

@Repository
public interface BooksRepositoryCustom {

    public Books findRandomBook();

}
