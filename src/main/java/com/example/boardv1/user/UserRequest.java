package com.example.boardv1.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    @Data
    public static class LoginDTO {
        // Login시 사용할 DTO
        @NotBlank(message = "유저네임을 입력해주세요.")
        private String username;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Data
    public static class JoinDTO {
        // join 시 사용할 DTO
        @NotBlank(message = "유저네임을 입력해주세요.")
        @Size(min = 3, max = 20, message = "유저네임은 3~20자 사이로 입력해주세요.")
        private String username;
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 3, max = 20, message = "비밀번호는 3~20자 사이로 입력해주세요.")
        private String password;
        @NotBlank(message = "이메일 형식이 올바르지 않습니다.")
        private String email;
    }

}
