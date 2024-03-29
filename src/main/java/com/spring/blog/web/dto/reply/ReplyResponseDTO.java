package com.spring.blog.web.dto.reply;

import com.spring.blog.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class ReplyResponseDTO {
    private int replyId;
    private String replyWriter;
    private String replyContent;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;

    public ReplyResponseDTO(Reply reply){
        this.replyId = reply.getReplyId();
        this.replyWriter=reply.getReplyWriter();
        this.replyContent = reply.getReplyContent();
        this.publishedAt = reply.getPublishedAt();
        this.updatedAt = reply.getUpdatedAt();
    }

    public Reply toEntity(ReplyResponseDTO responseDTO){
        return Reply.builder()
                .replyId(this.replyId)
                .replyWriter(this.replyWriter)
                .replyContent(this.replyContent)
                .build();
    }

}
