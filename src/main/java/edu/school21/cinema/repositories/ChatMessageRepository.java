package edu.school21.cinema.repositories;

import edu.school21.cinema.models.entity.ChatMessage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ChatMessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(ChatMessage chatMessage) {
        entityManager.persist(chatMessage);
    }

    public ChatMessage findById(Long id) {
        return entityManager.find(ChatMessage.class, id);
    }

    public List<ChatMessage> findByFilmId(Integer id) {
        return entityManager
                .createQuery("select c from ChatMessage c where c.film.id = ?1 order by c.created desc", ChatMessage.class)
                .setMaxResults(20)
                .setParameter(1, id)
                .getResultList();
    }
}
