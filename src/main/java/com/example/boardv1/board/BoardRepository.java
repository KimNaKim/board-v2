package com.example.boardv1.board;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em; // 인터페이스, 의존성 주입

    // Repository
    public Optional<Board> findById(int id) {
        try {
            Board board = em.find(Board.class, id);
            return Optional.of(board);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> findBoards = query.getResultList(); // 객체지향쿼리
        System.out.println("============findAll done");
        return findBoards;
    }

    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public void delete(Board board) {
        em.remove(board);
    }

    // Board를 id로 조회하되, 연관된 User도 같이 가져와서 Optional로 반환하는 코드
    // 기존 findById()를 사용하고 User를 뒤늦게 조회하면 User Lazy가 발생 (나중에 접근 시 문제가 발생할 수 있음)
    public Optional<Board> findByIdJoinUser(int id) {
        // jpql
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        try {
            Board board = (Board) query.getSingleResult();
            System.out.println("===========findByIdJoinUser done");
            return Optional.of(board);
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

}
