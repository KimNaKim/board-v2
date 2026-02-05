package com.example.boardv1.board;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boardv1.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bService; // 의존성 주입
    private final HttpSession session;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        // 인증x 권한x
        List<Board> list = bService.findAll();
        req.setAttribute("models", list);
        return "index";
    }

    // 게시글 작성 페이지
    @GetMapping("/boards/save-form")
    public String saveForm() {
        // 인증o 권한x
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        if (sessionUser == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }
        return "board/save-form";
    }

    // 게시글 작성 버튼 누르면 실행됨
    @PostMapping("/boards/save")
    public String save(BoardRequest.SaveOrUpdateDTO reqDTO) throws IOException {
        // 인증o 권한x
        String title = reqDTO.getTitle();
        String content = reqDTO.getContent();
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        if (sessionUser == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }
        // 작성한 게시글 상세 페이지로 이동하기(확인목적)
        int id = bService.insert(title, content, sessionUser).getId();
        return "redirect:/boards/" + id;
    }

    // 게시글 수정 페이지
    @GetMapping("/boards/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest req) {
        // 인증o 권한o
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        if (sessionUser == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }
        Board board = bService.updateFormBoardInfo(id, sessionUser.getId());
        req.setAttribute("model", board);
        return "board/update-form";
    }

    // 게시글 수정 버튼 누르면 실행됨
    @PostMapping("/boards/{id}/update")
    public String update(@PathVariable("id") int id, BoardRequest.SaveOrUpdateDTO reqDTO) {
        // 인증o 권한o
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        if (sessionUser == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }
        bService.update(id, reqDTO.getTitle(), reqDTO.getContent(), sessionUser.getId());
        return "redirect:/boards/" + id;
    }

    // 상세 페이지
    @GetMapping("/boards/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest req) {
        // 인증x 권한x
        // session에 값이 있는지 확인 - 없을 경우 null, 있을 경우 로그인된 유저의 id 저장
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        // dto에 상세보기 결과값 저장
        BoardResponse.DetailDTO dto = bService.detail(id, sessionUserId);
        // req에 attribute 저장
        req.setAttribute("model", dto);
        return "board/detail";
    }

    // 게시글 삭제 버튼 누르면 실행됨
    @PostMapping("/boards/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        // 인증o 권한o
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 인증 (로그인 유무 확인)
        if (sessionUser == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }
        bService.delete(id, sessionUser.getId());
        return "redirect:/";
    }

    // JSON 확인용
    @GetMapping("/api/boards/{id}")
    public @ResponseBody BoardResponse.DetailDTO apiDetail(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Integer sessionUserId = sessionUser == null ? null : sessionUser.getId();
        BoardResponse.DetailDTO dto = bService.detail(id, sessionUserId);
        return dto;
    }

}
