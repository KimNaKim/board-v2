package com.example.boardv1.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        // 화면에 보이지 않는 것
        private int boardId;
        private int userId;

        // 화면에 보이는 것
        private String title;
        private String content;
        private String username;

        // 연산해야 하는 것
        private boolean isOwner; // 게시글의 주인인지

        public DetailDTO(Board board, Integer sessionUserId) {
            this.boardId = board.getId();
            this.userId = board.getUser().getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.username = board.getUser().getUsername();
            this.isOwner = board.getUser().getId() == sessionUserId;
        }
    }
}
