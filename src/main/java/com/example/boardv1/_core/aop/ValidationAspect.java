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
public class ValidationAspect {

    // 포인트컷: 모든 컨트롤러의 모든 메서드
    @Pointcut("execution(* com.example.boardv1..*Controller.*(..))")
    public void controllerMethods() {
    }

    // @Before: 컨트롤러 메서드 실행 전에 가로채기
    @Before("controllerMethods()")
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
