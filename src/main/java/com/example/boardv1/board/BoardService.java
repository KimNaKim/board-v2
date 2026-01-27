package com.example.boardv1.board;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final이 붙어 있는 모든 필드를 초기화하는 생성자를 만들라고 spring에게 지시하는 어노테이션
public class BoardService {
    private final BoardRepository bRepository; // 의존성 주입

    // 함수 정의

    public void insert(String title, String content) {
        Board board = new Board();
        board.setContent(content);
        board.setTitle(title);
        bRepository.save(board);
    }

    public void delete(int id) {
        Board board = bRepository.findById(id);
        bRepository.delete(board);
    }

    public void detail(int id) {
        bRepository.findById(id);
    }

    public void update(int id, String title, String content) {
        bRepository.update(id, title, content);
    }
}
