package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;

@Repository
public class SessionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Session> findAll() {
        return entityManager
                .createQuery("select s from Session s", Session.class)
                .getResultList();
    }

    public List<Session> findByName(String name) {
        String lowerName = "%" + name.toLowerCase(Locale.getDefault()) + "%";
        return entityManager
                .createQuery("select s from Session s " +
                        "where lower(s.film.title) like ?1", Session.class)
                .setParameter(1, lowerName)
                .getResultList();
    }

    @Transactional
    public void save(Session entity) {
        entityManager.persist(entity );
    }

    public Session findById(Integer sessionId) {
        return entityManager.find(Session.class, sessionId);
    }
}
