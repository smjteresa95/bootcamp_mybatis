package com.spring.blog.web.dto.blog;

import com.spring.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@AllArgsConstructor @Builder
public class BlogResponseDTO {

    //entity 클래스는 DB와 직접 연결이 되어있기 때문에
    //entity를 직접적으로 이용하거나 변경 할 일이 생기면 여러 클래스에 영향을 끼치게 되어
    //데이터 교환을 위한 객체를 따로 만들어 entity 대신 사용합니다.

    private final Long blogId; //숫자는 어지간하면 Long type을 쓴다.
    private final String writer;
    private final String blogTitle;
    private final String blogContent;
    private final LocalDateTime publishedAt;
    private final LocalDateTime updatedAt;
    private final Long blogCount;


    public BlogResponseDTO(Blog blog) {
        this.blogId = blog.getBlogId();
        this.writer = blog.getWriter();
        this.blogTitle = blog.getBlogTitle();
        this.blogContent = blog.getBlogContent();
        this.publishedAt = blog.getPublishedAt();
        this.updatedAt = blog.getUpdatedAt();
        this.blogCount = blog.getBlogCount();
    }
}
