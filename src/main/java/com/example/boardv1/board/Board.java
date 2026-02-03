package com.example.boardv1.board;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.example.boardv1.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

//DB 세상의 테이블을 자바 세상에 모델링하면 엔티티가 된다.
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 디폴트 생성자를 생성해준다
@Data // Getter와 Setter, toString 메서드를 자동으로 생성해준다
@Entity // 해당 어노테이션이 붙어있는 클래스는, 컴포넌트 스캔 후 DB 테이블을 자동으로 생성한다
@Table(name = "board_tb")
public class Board { // user 1, board n

    @Id // primary 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_INCREMENT 지정
    private Integer id;
    private String title;
    private String content;

    // private Integer userId;
    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

}
