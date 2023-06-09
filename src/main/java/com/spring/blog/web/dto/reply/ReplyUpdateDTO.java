package com.spring.blog.web.dto.reply;

import com.spring.blog.entity.Reply;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
public class ReplyUpdateDTO {
    private int replyId;
    private String replyContent;

    public ReplyUpdateDTO(Reply reply){
        this.replyId = reply.getReplyId();
        this.replyContent = reply.getReplyContent();
    }
}
