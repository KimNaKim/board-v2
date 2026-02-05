package com.example.boardv1.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardv1.board.Board;
import com.example.boardv1.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional
    public Reply insert(String comment, Integer boardId, User sessionUser) {
        Reply reply = new Reply();
        reply.setComment(comment);
        reply.setUser(sessionUser);

        Board board = replyRepository.getReferenceById(boardId);
        reply.setBoard(board);

        Reply GReply = replyRepository.save(reply);
        return GReply;
    }

    @Transactional
    public Integer delete(int id, int sessionUserId) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        // 권한 체크
        if (sessionUserId != reply.getUser().getId())
            throw new RuntimeException("삭제 권한이 없습니다.");
        int boardId = replyRepository.getReferenceById(reply.getBoard().getId()).getId();
        replyRepository.delete(reply);
        return boardId;
    }

}
