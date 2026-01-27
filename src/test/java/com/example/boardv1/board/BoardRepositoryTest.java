package com.example.boardv1.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import jakarta.persistence.EntityManager;

@Import(BoardRepository.class) // AutoWired로 가져올 클래스 Import하는 어노테이션
@DataJpaTest // EntityManager를 IoC에 등록하는 어노테이션
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.findById(id);

        // eye
        System.out.println(board);
    }

    @Test
    public void findByIdV2_test() {
        // given
        int id = 1;

        // when
        Board board = boardRepository.findById(id);
        em.clear();
        boardRepository.findById(id);

        // eye
        System.out.println(board);
    }

    @Test
    public void findAll_test() {
        // when
        List<Board> boards = boardRepository.findAll();

        // eye
        for (Board b : boards) {
            System.out.println(b);
        }
    }

    @Test
    public void findAllV2_test() {
        // given

        // when
        boardRepository.findAllV2();

        // eye
    }

    @Test
    public void save_test() {
        // given
        Board board = new Board();
        board.setTitle("title7");
        board.setContent("content7");
        System.out.println("======= before persist");
        System.out.println(board);

        // when
        boardRepository.save(board);

        // eye (board 객체가 DB 데이터와 동기화되었음)
        System.out.println("======= after persist");
        System.out.println(board);
    }

    @Test
    public void delete_test() {
        // given
        int id = 6;
        Board board = boardRepository.findById(id);

        // when
        boardRepository.delete(board);

        // eye
        em.flush(); // 더티체킹과 관련
    }

    @Test
    public void update_test() {
        // given
        Board board = boardRepository.findById(1);

        // when
        board.setTitle("title1-update");
        board.setContent("content1-update");
        em.flush();

        // eye
        List<Board> boards = boardRepository.findAll();

        for (Board b : boards) {
            System.out.println(b);
        }
    }

}
