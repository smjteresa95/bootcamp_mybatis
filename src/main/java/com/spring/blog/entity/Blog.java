package com.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;

import java.util.Date;
import java.util.Map;

// 역직렬화(DB -> 자바 객체)가 가능 하도록 blog table 구조에 맞춰서 멤버변수 선언
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class Blog {

    private Long blogId; //숫자는 어지간하면 Long type을 쓴다.
    private String writer;
    private String blogTitle;
    private String blogContent;
    private Date publishedAt;
    private Date updatedAt;
    private Integer blogCount;

    private Map<Long, Blog> blogMap;


//    public Blog(Long blogId, String writer, String blogTitle, String blogContent, Date publishedAt, Date updatedAt, Integer blogCount){
//        this.blogId=blogId;
//        this.writer=writer;
//        this.blogTitle=blogTitle;
//        this.blogContent=blogContent;
//        this.publishedAt=publishedAt;
//        this.updatedAt=updatedAt;
//        this.blogCount=blogCount;
//    }

}
