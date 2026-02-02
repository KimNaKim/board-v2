package com.example.boardv1.board;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em; // 인터페이스, 의존성 주입

    // Repository
    public Board findById(int id) {
        // select * from board_tb where id = 1;
        // ResultSet rs -> Board 객체 옮기기 (Object Mapping)
        // Board board = new Board();
        // board.id = rs.getInt("id");
        Board findBoard = em.find(Board.class, id);
        return findBoard;
    }

    public List<Board> findAll() {
        Query query = (Query) em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> findBoards = query.getResultList(); // 객체지향쿼리
        return findBoards;
    }

    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public void delete(Board board) {
        em.remove(board);
    }

}
