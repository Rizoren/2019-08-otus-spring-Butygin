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
    public List<Authors> findAllAuthors() {
        return em.createQuery("select b from Authors b", Authors.class).getResultList();
    }

    @Override
    public List<Genres> findAllGenres() {
        return em.createQuery("select b from Genres b", Genres.class).getResultList();
    }

    @Override
    public List<Books> findAllBooks() {
        return em.createQuery("select b from Books b", Books.class).getResultList();
    }

    @Override
    public List<Books> findAllByAuthorID(long id) {
        return em.createQuery("select b from Books b inner join b.authors a where a.author_id = :id", Books.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<Books> findAllByGenreID(long id) {
        return em.createQuery("select b from Books b inner join b.genres g where g.genre_id = :id", Books.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public Books findRandomBook() {
        long id = Long.valueOf(
                em.createNativeQuery("select cast(random_between(min(book_id),max(book_id)) as bigint) as book_id from books")
                        .getSingleResult().toString());

        Books books = em.createQuery("select b from Books b where b.book_id = :id", Books.class)
                .setParameter("id", id).getSingleResult();
        books.setAuthors(em.createQuery("select a from Books b inner join b.authors a where b.book_id = :id ", Authors.class)
                .setParameter("id", id).getResultList());
        books.setGenres(em.createQuery("select g from Books b inner join b.genres g where b.book_id = :id ", Genres.class)
                .setParameter("id", id).getResultList());
        return books;
    }
}

