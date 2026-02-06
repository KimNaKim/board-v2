package com.example.boardv1._core.errors.ex;

//페이지(자원)을 찾을 수 없을 때 생성되는 exception
public class Exception404 extends RuntimeException {

    public Exception404(String message) {
        super(message);
    }

}
