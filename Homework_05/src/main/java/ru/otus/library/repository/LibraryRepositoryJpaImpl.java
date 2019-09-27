package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LibraryRepositoryJpaImpl implements LibraryRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Books> findAllBooks() {
        return em.createQuery("select b from Books b", Books.class).getResultList();
    }

    @Override
    public List<Authors> findAllAuthors() {
        return em.createQuery("select b from Authors b", Authors.class).getResultList();
    }

    @Override
    public List<Genres> findAllGenres() {
        return em.createQuery("select b from Genres b", Genres.class).getResultList();
    }
}
