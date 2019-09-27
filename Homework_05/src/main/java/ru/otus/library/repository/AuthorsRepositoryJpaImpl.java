package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Authors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorsRepositoryJpaImpl implements AuthorsRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Authors> findById(long id) {
        return Optional.ofNullable(em.find(Authors.class, id));
    }

    @Override
    public List<Authors> findAll() {
        return em.createQuery("select b from Authors b", Authors.class).getResultList();
    }

    @Override
    public Authors save(Authors authors) {
        if (authors.getAuthor_id() <= 0) {
            em.persist(authors);
            return authors;
        } else {
            return em.merge(authors);
        }
    }

    @Override
    public void delete(Authors authors) {
        em.remove(authors);
    }
}
