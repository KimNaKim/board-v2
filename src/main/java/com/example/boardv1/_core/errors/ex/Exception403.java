package com.example.boardv1._core.errors.ex;

//권한 실패 시 생성되는 exception
public class Exception403 extends RuntimeException {

    public Exception403(String message) {
        super(message);
    }

}
