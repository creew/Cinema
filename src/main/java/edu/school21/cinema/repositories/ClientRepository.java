package edu.school21.cinema.repositories;

import edu.school21.cinema.models.entity.ChatMessage;
import edu.school21.cinema.models.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class ClientRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Client client) {
        entityManager.persist(client);
    }

    public Client findBySessionId(UUID sessionId) {
        try {
            return entityManager
                    .createQuery("select c from Client c where c.sessionId = ?1", Client.class)
                    .setParameter(1, sessionId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
