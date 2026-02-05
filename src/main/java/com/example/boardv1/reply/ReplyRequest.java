package com.example.boardv1.reply;

import lombok.Data;
import lombok.NoArgsConstructor;

public class ReplyRequest {
    @Data
    @NoArgsConstructor
    public static class SaveOrUpdateDTO {
        private Integer boardId;
        private String comment;

    }

}
