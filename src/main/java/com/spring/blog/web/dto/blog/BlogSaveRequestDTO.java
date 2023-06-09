package com.spring.blog.web.dto.blog;


import com.spring.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

//entity 클래스는 DB 테이블에 대응하는 자바 클래스이고, 데이터 전달을 위한 목적보다는 정의를 위해서 쓰는 경우가 많다.
//따라서 실질적으로 역직렬화나 직렬화를 위한 로직에는 DTO라는 클래스를 만들고 활용할 쿼리문에 맞춰서 멤버변수를 정의해 사용한다.
@Getter
@AllArgsConstructor @NoArgsConstructor
public class BlogSaveRequestDTO {
    private Long blogId;
    private String writer;
    private String blogTitle;
    private String blogContent;
    private Date publishedAt;
    private Date updatedAt;
    private Integer blogCount;

    public Blog toEntity(){
        return Blog.builder()
                .blogId(blogId)
                .writer(writer)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .publishedAt(publishedAt)
                .updatedAt(updatedAt)
                .blogCount(blogCount)
                .build();
    }

}
