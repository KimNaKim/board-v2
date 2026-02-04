package com.example.boardv1.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardv1.user.User;

import lombok.RequiredArgsConstructor;

//책임 : 트랜잭션 관리, DTO 만들기, 권한 체크
@Service
@RequiredArgsConstructor // final이 붙어 있는 모든 필드를 초기화하는 생성자를 만들라고 spring에게 지시하는 어노테이션
public class BoardService {
    private final BoardRepository bRepository; // 의존성 주입

    // 함수 정의
    // 쓰기 코드에는 반드시 Transactional 어노테이션 필수!!
    @Transactional // 원자성 보장을 위해 모든 게 통과했을 때만 commit, 하나라도 실패하면 rollback
    public Board insert(String title, String content, User sessionUser) {
        // 1. 비영속 객체 (before persist)
        Board board = new Board();
        board.setContent(content);
        board.setTitle(title);
        board.setUser(sessionUser);
        // 2. persist (영속화시키기)
        Board GBoard = bRepository.save(board);
        return GBoard;
    }

    // 게시글삭제
    @Transactional
    public void delete(int id, int sessionUserId) {
        Board board = bRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // 권한 체크
        if (sessionUserId != board.getUser().getId())
            throw new RuntimeException("삭제 권한이 없습니다.");
        bRepository.delete(board);
    }

    // 게시글수정
    @Transactional
    public void update(int id, String title, String content, int sessionUserId) {
        Board board = bRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // 권한 체크
        if (sessionUserId != board.getUser().getId())
            throw new RuntimeException("수정 권한이 없습니다.");
        board.setTitle(title);
        board.setContent(content);
    }

    // 게시글상세보기
    public BoardResponse.DetailDTO detail(int id, Integer sessionUserId) {
        Board board = bRepository.findByIdJoinUser(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return new BoardResponse.DetailDTO(board, sessionUserId);
    }

    // 게시글 전체조회
    public List<Board> findAll() {
        List<Board> list = bRepository.findAll();
        return list;
    }

    // 수정 폼 게시글의 정보 가져오기
    public Board updateFormBoardInfo(int id, int sessionUserId) {
        Board board = bRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 권한 체크
        if (sessionUserId != board.getUser().getId())
            throw new RuntimeException("수정 권한이 없습니다.");
        return board;
    }
}
