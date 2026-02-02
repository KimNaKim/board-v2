package com.example.boardv1.user;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 오브젝트 매핑을 Hibernate가 실행 시 디폴트 생성자를 new한다.
@Data
@Entity
@Table(name = "user_tb")
public class User {
    @Id // primary 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_INCREMENT 지정
    private Integer id;
    private String userName;
    private String password;
    private String email;

    private LocalDateTime createdAt;

}
