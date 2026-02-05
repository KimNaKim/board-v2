package com.example.boardv1.reply;

import lombok.Data;

public class ReplyResponse {

    @Data // 내부 클래스로 만들기
    public static class DTO {
        // 댓글의 id
        private Integer id;
        // 댓글 작성자 id
        private Integer replyUserId;
        private String comment;
        private String replyUsername;
        // 댓글 작성자 구별
        private boolean isReplyOwner;

        public DTO(Reply reply, Integer sessionUserId) {
            this.id = reply.getId();
            this.replyUserId = reply.getUser().getId();
            this.comment = reply.getComment();
            this.replyUsername = reply.getUser().getUsername();
            this.isReplyOwner = reply.getUser().getId() == sessionUserId;
        }

    }

}
