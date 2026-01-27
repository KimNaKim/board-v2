package com.example.boardv1.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService bService; // 의존성 주입

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/boards/save-form")
    public String saveForm() {
        bService.insert("", "");
        return "board/save-form";
    }

    @GetMapping("/boards/{id}/update-form")
    public String updateForm() {
        return "board/update-form";
    }

    @GetMapping("/boards/{id}")
    public String detail(@PathVariable("id") int id) {
        bService.detail(id);
        return "board/detail";
    }

    @GetMapping("/boards/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        bService.delete(id);
        return "index";
    }

}
