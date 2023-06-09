package com.spring.blog.web.dto.blog;

import com.spring.blog.entity.Blog;
import lombok.Getter;

import java.util.Date;
@Getter
public class BlogResponseDTO {

    //entity 클래스는 DB와 직접 연결이 되어있기 때문에
    //entity를 직접적으로 이용하거나 변경 할 일이 생기면 여러 클래스에 영향을 끼치게 되어
    //데이터 교환을 위한 객체를 따로 만들어 entity 대신 사용합니다.

    private Long blogId; //숫자는 어지간하면 Long type을 쓴다.
    private String writer;
    private String blogTitle;
    private String blogContent;
    private Date publishedAt;
    private Date updatedAt;
    private Integer blogCount;

    public BlogResponseDTO(Blog entity){
        this.blogId = entity.getBlogId();
        this.writer = entity.getWriter();
        this.blogTitle = entity.getBlogTitle();
        this.blogContent = entity.getBlogContent();
        this.publishedAt = entity.getPublishedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.blogCount = entity.getBlogCount();
    }
}
