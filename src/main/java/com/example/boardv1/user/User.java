package com.example.boardv1.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
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
    @Column(unique = true, nullable = false) // 중복을 방지함 - pk, uk일 때 인덱스를 자동으로 만들어준다.
    private String username;
    @Column(nullable = false, length = 100) // 입력값이 null일 수 없다.
    private String password;
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
