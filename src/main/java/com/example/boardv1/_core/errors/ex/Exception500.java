package com.example.boardv1._core.errors.ex;

// 서버 측 오류일 때 생성되는 exception
public class Exception500 extends RuntimeException {

    public Exception500(String message) {
        super(message);
    }

}
