package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Film> findAll() {
        return entityManager
                .createQuery("select f from Film f", Film.class)
                .getResultList();
    }

    @Transactional
    public void save(Film entity) {
        entityManager.persist(entity );
    }
}
