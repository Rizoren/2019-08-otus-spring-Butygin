package ru.otus.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    /*
    Optional<Books> findById(long id);
    List<Books> findAll();
    Books save(Books books);
    void delete(Books books);*/
}
