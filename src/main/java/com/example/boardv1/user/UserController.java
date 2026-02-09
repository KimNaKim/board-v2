package com.example.boardv1.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boardv1._core.errors.ex.Exception400;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService uService;
    private final HttpSession session;

    // 로그인 페이지
    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    // 로그인 버튼을 누를때 작동
    @PostMapping("/login")
    public String login(@Valid UserRequest.LoginDTO reqDto, HttpServletResponse resp, Errors errors) {
        User sessionUser = uService.login(reqDto.getUsername(), reqDto.getPassword());
        session.setAttribute("sessionUser", sessionUser);

        // 유효성 검사 실패 시
        if (errors.hasErrors()) {
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        // http Response header에 Set-Cookie : sessionKey 저장돼서 응답
        Cookie cookie = new Cookie("username", sessionUser.getUsername());
        cookie.setHttpOnly(false);
        resp.addCookie(cookie);

        return "redirect:/";
    }

    // 로그아웃 버튼을 누를 때 작동
    @GetMapping("/logout")
    public String logout() {
        // 로그인 상태일 경우 쿠키에서 유저 정보를 삭제
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    // 회원가입 버튼을 누를 때 작동
    @PostMapping("/join")
    public String join(@Valid UserRequest.JoinDTO reqDto, Errors errors) {
        // 유효성 검사 실패 시
        if (errors.hasErrors()) {
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        uService.insert(reqDto.getUsername(), reqDto.getPassword(), reqDto.getEmail());
        // uService.findAll();
        return "redirect:/login-form";
    }
}
