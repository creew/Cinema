package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Session> findAll() {
        return entityManager
                .createQuery("select s from Session s", Session.class)
                .getResultList();
    }

    @Transactional
    public void save(Session entity) {
        entityManager.persist(entity );
    }
}
