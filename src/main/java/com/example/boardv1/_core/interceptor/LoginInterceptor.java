package com.example.boardv1._core.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.boardv1._core.errors.ex.Exception401;
import com.example.boardv1.user.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception { // 뷰가 완성되고 난 다음 - 쓸 일이 별로 x
        System.out.println("=============== view Render complete ================");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception { // 컨트롤러와 메서드 호출 직전 - /boards, /replies
        // 로그인 여부를 확인하기 - 실행 결과가 true면 메서드 진입
        String uri = request.getRequestURI();
        if (uri.matches(".*/boards/\\d+$")) {
            return true; // 메서드 진입
        }

        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new Exception401("인증되지 않았습니다.");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception { // 컨트롤러와 메서드 호출 직후
        System.out.println("================postHandle complete===============");

    }

}
