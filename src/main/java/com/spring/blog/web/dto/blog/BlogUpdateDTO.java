package com.spring.blog.web.dto.blog;

import com.spring.blog.entity.Blog;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor @Builder
@NoArgsConstructor
//한번 만들어진 entity는 변하지 않는다. 데이터의 불변성을 유지하기 위해서 DTO를 만들어쓴다.

public class BlogUpdateDTO {
    //업데이트시 필요한 데이터:

    private Long blogId;
    private String writer;
    private String blogTitle;
    private String blogContent;

    //entity data를 DTO로 변환해주기 위한 생성자
    //entity는 dto의 구조와 상관없이 작동해야 하므로 entity를 dto로 바꾸는 건 가능해야 하지만 그 반대는 성립하지 않는다.
    public BlogUpdateDTO(Blog blog){
        this.blogId = blog.getBlogId();
        this.writer = blog.getWriter();
        this.blogTitle = blog.getBlogTitle();
        this.blogContent = blog.getBlogContent();
    }




}
