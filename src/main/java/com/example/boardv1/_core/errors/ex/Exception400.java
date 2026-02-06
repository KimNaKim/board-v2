package com.example.boardv1._core.errors.ex;

//유효성 검사 실패 시 사용하는 Exception 클래스
public class Exception400 extends RuntimeException {

    public Exception400(String message) {
        super(message);
    }
    
}
