package edu.school21.cinema.repositories;

import edu.school21.cinema.models.entity.FileDescription;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class FileDescriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public FileDescription findById(UUID id) {
        return entityManager.find(FileDescription.class, id);
    }

    @Transactional
    public void save(FileDescription fileDescription) {
        entityManager.persist(fileDescription);
    }
}
