package com.example.boardv1.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("")
    public String m1() {
        return "";
    }

}
