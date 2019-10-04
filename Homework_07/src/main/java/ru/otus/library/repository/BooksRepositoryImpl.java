package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BooksRepositoryImpl implements BooksRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public Books findRandomBook() {
        long id = Long.valueOf(
                em.createNativeQuery("select random_between(min(b2.book_id),max(b2.book_id)) from Books b2")
                        .getSingleResult().toString());

        Books books = em.createQuery("select b from Books b where b.book_id = :id", Books.class)
                .setParameter("id", id).getSingleResult();
        books.setAuthors(em.createQuery("select a from Books b inner join b.authors a where b.book_id = :id ", Authors.class)
                .setParameter("id", books.getBook_id()).getResultList());
        books.setGenres(em.createQuery("select g from Books b inner join b.genres g where b.book_id = :id ", Genres.class)
                .setParameter("id", books.getBook_id()).getResultList());
        return books;
    }
}

