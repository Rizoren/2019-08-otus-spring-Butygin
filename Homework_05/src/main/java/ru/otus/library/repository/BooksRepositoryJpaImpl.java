package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;
import ru.otus.library.model.Books;
import ru.otus.library.model.Genres;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksRepositoryJpaImpl implements BooksRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Books> findById(long id) {
        return Optional.ofNullable(em.find(Books.class, id));
    }

    @Override
    public List<Books> findAll() {
        return em.createQuery("select b from Books b", Books.class).getResultList();
    }

    @Override
    public Books save(Books books) {
        if (books.getBook_id() <= 0) {
            em.persist(books);
            return books;
        } else {
            return em.merge(books);
        }
    }

    @Override
    public void delete(Books books) {
        em.remove(books);
    }
}
