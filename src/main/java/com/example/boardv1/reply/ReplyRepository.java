package com.example.boardv1.reply;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.boardv1.board.Board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    private final EntityManager em;

    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }

    public void delete(Reply reply) {
        em.remove(reply);
    }

    public Optional<Reply> findById(int id) {
        return Optional.ofNullable(em.find(Reply.class, id));
    }

}
