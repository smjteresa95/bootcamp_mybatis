package com.spring.blog.web.dto.reply;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @Builder
@NoArgsConstructor
@ToString
public class ReplySaveDTO {
    private long blog;
    private String replyWriter;
    private String replyContent;
}
