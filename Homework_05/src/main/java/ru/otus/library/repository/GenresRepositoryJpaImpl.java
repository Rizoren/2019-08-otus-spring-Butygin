package ru.otus.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genres;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class GenresRepositoryJpaImpl implements GenresRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Genres> findById(long id) {
        return Optional.ofNullable(em.find(Genres.class, id));
    }

    @Override
    public List<Genres> findAll() {
        return em.createQuery("select b from Genres b", Genres.class).getResultList();
    }

    @Override
    public Genres save(Genres genres) {
        if (genres.getGenre_id() <= 0) {
            em.persist(genres);
            return genres;
        } else {
            return em.merge(genres);
        }
    }

    @Override
    public void delete(Genres  genres) {
        em.remove(genres);
    }
}
