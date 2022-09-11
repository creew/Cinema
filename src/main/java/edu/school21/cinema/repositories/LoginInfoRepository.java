package edu.school21.cinema.repositories;

import edu.school21.cinema.models.entity.Hall;
import edu.school21.cinema.models.entity.LoginInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LoginInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(LoginInfo loginInfo) {
        entityManager.persist(loginInfo);
    }

    public LoginInfo findById(Long id) {
        return entityManager.find(LoginInfo.class, id);
    }

    public List<LoginInfo> findByUserId(Integer id) {
        return entityManager
                .createQuery("select l from LoginInfo l where l.client.id = ?1 order by l.loginTime desc", LoginInfo.class)
                .setParameter(1, id)
                .setMaxResults(10)
                .getResultList();
    }
}
