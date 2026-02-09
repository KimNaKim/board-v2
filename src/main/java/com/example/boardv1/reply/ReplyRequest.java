package com.example.boardv1.reply;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReplyRequest {
    @Data
    @NoArgsConstructor
    public static class SaveOrUpdateDTO {
        private Integer boardId;
        @NotBlank(message = "댓글 내용을 입력하세요.")
        private String comment;

    }

}
