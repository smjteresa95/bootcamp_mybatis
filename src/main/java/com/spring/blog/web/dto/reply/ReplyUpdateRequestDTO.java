package com.spring.blog.web.dto.reply;

import lombok.*;


@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
public class ReplyUpdateRequestDTO {
    private int replyId;
    private String replyWriter;
    private String replyContent;

    public ReplyUpdateRequestDTO(ReplyResponseDTO replyResponseDTO){
        this.replyId = replyResponseDTO.getReplyId();
        this.replyWriter = replyResponseDTO.getReplyWriter();
        this.replyContent = replyResponseDTO.getReplyContent();
    }

}
