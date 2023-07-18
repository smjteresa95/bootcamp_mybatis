package com.spring.blog.entity;

import com.spring.blog.web.dto.reply.ReplyUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor @Builder
@Getter @ToString
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    @Column(nullable = false)
    private long blogId;

    @Column(nullable = false)
    private String replyWriter;

    @Column(nullable = false)
    private String replyContent;

    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void setDefaultValue(){
        this.publishedAt = LocalDateTime.parse("YYYY:mm:DD HH:MM:SS");
        this.updatedAt = LocalDateTime.parse("YYYY:mm:DD HH:MM:SS");
    }

    @PreUpdate
    public void setUpdatedAt(){
        this.updatedAt = LocalDateTime.parse("YYYY:mm:DD HH:MM:SS");

    }

    public ReplyUpdateRequestDTO toUpdateRequestDTO(Blog blog){
        return ReplyUpdateRequestDTO.builder()
                .replyId(this.replyId)
                .replyWriter(this.replyWriter)
                .replyContent(this.replyContent)
                .build();
    }
}
