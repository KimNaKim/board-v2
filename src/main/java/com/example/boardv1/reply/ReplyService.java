package com.example.boardv1.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardv1._core.errors.ex.Exception400;
import com.example.boardv1._core.errors.ex.Exception403;
import com.example.boardv1.board.Board;
import com.example.boardv1.board.BoardRepository;
import com.example.boardv1.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Reply insert(ReplyRequest.SaveOrUpdateDTO reqDTO, User sessionUser) {
        Reply reply = new Reply();
        reply.setComment(reqDTO.getComment());
        reply.setUser(sessionUser);

        Board board = boardRepository.getReferenceById(reqDTO.getBoardId());
        reply.setBoard(board);

        return replyRepository.save(reply);
    }

    @Transactional
    public void delete(int id, int sessionUserId) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new Exception400("댓글을 찾을 수 없습니다."));
        // 권한 체크
        if (sessionUserId != reply.getUser().getId())
            throw new Exception403("삭제 권한이 없습니다.");
        replyRepository.delete(reply);
    }

    public Reply findById(int id) {
        return replyRepository.findById(id).orElseThrow(() -> new Exception400("댓글을 찾지 못했습니다."));
    }

}
