package com.example.boardv1.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    // 회원가입 시 작동하는 메서드
    public User save(User user) {
        em.persist(user);
        return user;
    }

    public User findById(int id) {
        User findUser = em.find(User.class, id);
        return findUser;
    }

    // 로그인 시 username으로 조회하여 password 검증하기
    public User findByUsername(String username) {
        // 객체지향쿼리
        Query query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", username);
        System.out.println(query.getParameter("username"));
        try {
            User findUser = (User) query.getSingleResult();
            return findUser;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> findAll() {
        Query query = (Query) em.createQuery("select u from User u order by u.id", User.class);
        List<User> findUsers = query.getResultList(); // 객체지향쿼리
        return findUsers;
    }

}
