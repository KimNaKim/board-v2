package com.example.boardv1.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final이 붙어 있는 모든 필드를 초기화하는 생성자를 만들라고 spring에게 지시하는 어노테이션
public class BoardService {
    private final BoardRepository bRepository; // 의존성 주입

    // 함수 정의
    // 쓰기 코드에는 반드시 Transactional 어노테이션 필수!!
    @Transactional // 원자성 보장을 위해 모든 게 통과했을 때만 commit, 하나라도 실패하면 rollback
    public Board insert(String title, String content) {
        // 1. 비영속 객체 (before persist)
        Board board = new Board();
        board.setContent(content);
        board.setTitle(title);
        // 2. persist (영속화시키기)
        Board GBoard = bRepository.save(board);
        return GBoard;
    }

    @Transactional
    public void delete(int id) {
        Board board = bRepository.findById(id).get();
        bRepository.delete(board);
    }

    @Transactional
    public void update(int id, String title, String content) {
        Board board = bRepository.findById(id).get();
        board.setTitle(title);
        board.setContent(content);
    }

    public Board detail(int id) {
        Board board = bRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return board;
    }

    public List<Board> findAll() {
        List<Board> list = bRepository.findAll();

        return list;
    }
}
