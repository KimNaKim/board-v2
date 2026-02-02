package com.example.boardv1.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class LoginDTO {
        // Login시 사용할 DTO
        private String username;
        private String password;
    }

    @Data
    public static class JoinDTO {
        // join 시 사용할 DTO
        private String username;
        private String password;
        private String email;
    }

}
