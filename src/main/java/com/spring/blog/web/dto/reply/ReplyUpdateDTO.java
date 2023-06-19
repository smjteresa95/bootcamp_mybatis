package com.spring.blog.web.dto.reply;

import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
public class ReplyUpdateDTO {
    private int replyId;
    private String replyWriter;
    private String replyContent;

    public ReplyUpdateDTO(ReplyFindByIdDTO replyFindByIdDTO){
        this.replyId = replyFindByIdDTO.getReplyId();
        this.replyWriter = replyFindByIdDTO.getReplyWriter();
        this.replyContent = replyFindByIdDTO.getReplyContent();
    }
}
