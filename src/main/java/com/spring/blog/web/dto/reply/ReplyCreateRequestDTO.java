package com.spring.blog.web.dto.reply;

import com.spring.blog.entity.Reply;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
@ToString
public class ReplyCreateRequestDTO {
    private long blogId;
    private String replyWriter;
    private String replyContent;
    //쿼리문에서 now()를 어차피 해줄것이기 때문에 DTO 쪽에서 처리해 줄 필요가 없다.
    //updaatedAt은 DB 쪽에서 처리를 해주는 거지 자바 쪽에서 해줄 것이 아니다.

    public ReplyCreateRequestDTO(Reply reply){
        this.blogId = reply.getBlogId();
        this.replyWriter = reply.getReplyWriter();
        this.replyContent = reply.getReplyContent();
    }
}
