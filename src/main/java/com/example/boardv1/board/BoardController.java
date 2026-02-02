package com.example.boardv1.board;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bService; // 의존성 주입

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        List<Board> list = bService.findAll();
        req.setAttribute("models", list);
        return "index";
    }

    // 게시글 작성 페이지
    @GetMapping("/boards/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    // 게시글 작성 버튼 누르면 실행됨
    @PostMapping("/boards/save")
    public String save(BoardRequest.SaveOrUpdateDTO reqDTO) throws IOException {
        String title = reqDTO.getTitle();
        String content = reqDTO.getContent();
        // 작성한 게시글 상세 페이지로 이동하기(확인목적)
        int id = bService.insert(title, content).getId();
        return "redirect:/boards/" + id;
    }

    // 게시글 수정 페이지
    @GetMapping("/boards/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest req) {
        Board board = bService.detail(id);
        req.setAttribute("model", board);
        return "board/update-form";
    }

    // 게시글 수정 버튼 누르면 실행됨
    @PostMapping("/boards/{id}/update")
    public String update(@PathVariable("id") int id, BoardRequest.SaveOrUpdateDTO reqDTO) {
        bService.update(id, reqDTO.getTitle(), reqDTO.getContent());
        return "redirect:/boards/" + id;
    }

    // 상세 페이지
    @GetMapping("/boards/{id}")
    public String detail(@PathVariable("id") int id, HttpServletRequest req) {
        Board board = bService.detail(id);
        req.setAttribute("model", board);
        return "board/detail";
    }

    // 게시글 삭제 버튼 누르면 실행됨
    @PostMapping("/boards/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        bService.delete(id);
        return "redirect:/";
    }

}
