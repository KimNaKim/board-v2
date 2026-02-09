package com.example.boardv1.reply;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.example.boardv1.user.User;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    // 댓글쓰기
    @PostMapping("/replies/save")
    public String save(@Valid ReplyRequest.SaveOrUpdateDTO reqDTO, Errors errors) {
        // aop 클래스가 존재하기 때문에 자동으로 유효성 검사를 해준다.
        // 인증o 권한x
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        // 작성후 댓글 확인을 위해 리다이렉트
        int id = replyService.insert(reqDTO, sessionUser).getBoard().getId();
        return "redirect:/boards/" + id;
    }

    // 댓글삭제
    // 게시글 삭제 버튼 누르면 실행됨
    @PostMapping("/replies/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        // 인증o 권한o
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        Reply reply = replyService.findById(id);
        int boardId = reply.getBoard().getId();

        replyService.delete(id, sessionUser.getId());
        // 삭제후 댓글 확인을 위해 리다이렉트
        return "redirect:/boards/" + boardId;
    }
}
