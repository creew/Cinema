package edu.school21.cinema.repositories;

import edu.school21.cinema.models.LoginInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
