package com.example.boardv1.user;

import java.util.List;
import java.util.Optional;

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

    // 로그인할때 username으로 조회해서 password 검증
    public Optional<User> findByUsername(String username) {
        try {
            User user = em.createQuery("select u from User u where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    public List<User> findAll() {
        Query query = em.createQuery("select u from User u order by u.id", User.class);
        List<User> findUsers = query.getResultStream().toList(); // 객체지향쿼리
        return findUsers;
    }

}
