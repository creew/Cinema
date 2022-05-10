package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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

    public Client findById(UUID userId) {
        return entityManager.find(Client.class, userId);
    }
}
