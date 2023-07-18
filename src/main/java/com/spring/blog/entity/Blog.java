package com.spring.blog.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import com.spring.blog.web.dto.blog.BlogUpdateDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

// 역직렬화(DB -> 자바 객체)가 가능 하도록 blog table 구조에 맞춰서 멤버변수 선언
//실무에서는 Setter를 쓰지 않고 toDTO()형태의 메서드를 만들어 entity를 dto
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@DynamicInsert //null인 필드값에 기본값 지정된 요소를 집어넣어줌
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId; //숫자는 어지간하면 Long type을 쓴다.

    @Column(nullable=false)
    private String writer;

    @Column(nullable=false)
    private String blogTitle;

    @Column(nullable=false)
    private String blogContent;

    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
    @ColumnDefault("0")
    private Long blogCount; // Wrapper Long형을 사용, 기본형 변수는 null을 가질 수 없다.
    //DB에 커밋까지 된 상태를 영속상태라고 한다

    //@PrePersist annotation: insert, update 되기 전 실행 할 로직을 작성한다.
    @PrePersist
    public void setDefaultValue (){
        this.blogCount = this.blogCount == null? 0 : this.blogCount;
        this.publishedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    //@PreUpdate annotation: update 되기 전 실행할 로직을 작성한다.
    @PreUpdate
    public void setUpdateValue(){
        this.updatedAt = LocalDateTime.now();
    }


//    private Map<Long, Blog> blogMap;

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
