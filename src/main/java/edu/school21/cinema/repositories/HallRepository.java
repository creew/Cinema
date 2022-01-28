package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HallRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Hall> findAll() {
        return entityManager
                .createQuery("select h from Hall h", Hall.class)
                .getResultList();
    }

    @Transactional
    public void save(Hall entity) {
        entityManager.persist(entity );
    }

    public Hall findById(Integer hallId) {
        return entityManager.find(Hall.class, hallId);
    }
}
