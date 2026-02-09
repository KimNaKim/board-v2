package com.example.boardv1._core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.example.boardv1._core.errors.ex.Exception400;

@Aspect
@Component
public class ValidationHandler { // 모든 코드가 공통으로 가지고 있는 코드를 메서드로 분리시키기

    // @Before: 컨트롤러 메서드 실행 전에 가로채기
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void validationCheck(JoinPoint jp) {

        // 메서드의 모든 파라미터를 순회
        for (Object arg : jp.getArgs()) {
            // Errors 타입 파라미터를 찾으면
            if (arg instanceof Errors errors) {
                // 에러가 있으면 Exception400 throw
                if (errors.hasErrors()) {
                    throw new Exception400(
                            errors.getAllErrors().get(0).getDefaultMessage());
                }
            }
        }
    }
}
