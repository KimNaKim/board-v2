package com.example.boardv1.reply;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.boardv1.board.Board;
import com.example.boardv1.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 오브젝트 매핑을 Hibernate가 실행 시 디폴트 생성자를 new한다.
@Data
@Entity
@Table(name = "reply_tb")
public class Reply {

    /*
     * 게시글-댓글의 관계 = 1 : n
     * user-댓글의 관계 = 1 : n
     */

    @Id // primary 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_INCREMENT 지정
    private Integer id;
    private String comment;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Board board;

}
