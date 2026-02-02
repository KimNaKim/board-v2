package com.example.boardv1.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService uService;

    // 로그인 페이지
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    // 로그인 버튼을 누를때 작동
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDto) {
        uService.login(reqDto.getUsername(), reqDto.getPassword());
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    // 회원가입 버튼을 누를 때 작동
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDto) {
        uService.insert(reqDto.getUsername(), reqDto.getPassword(), reqDto.getEmail());
        return "redirect:/login-form";
    }
}
