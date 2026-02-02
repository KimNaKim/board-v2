package com.example.boardv1.board;

import lombok.Data;

public class BoardRequest {

    // 책임 : 클라이언트(브라우저)의 요청 데이터를 저장한다.
    @Data
    public static class SaveOrUpdateDTO {
        // insert&update시 사용할 DTO
        private String title;
        private String content;
    }
}
