package com.spring.blog.repository;

import com.spring.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

public interface BlogRepository {

    //테스트 전 사전에 정의해야하는 메서드들
    void createBlogTable(); //테이블 생성: 메서드 호출 시 CREATE TABLE 구문 실행
    void insertTestData(); //더미데이터 입력: Dummy data로 3개 row 데이터 입력하기
    void dropBlogTable(); //테이블 삭제: 단위 테스트 종료 후에 DB 초기화를 위해 테이블 삭제

    //전체 데이터 조회 기능 findAll
    //Blog entity 하나가 포스팅 row 하나를 받을 수 있고
    //n 개의 복수의 Blog entity를 받아와야 하므로 List 로 감싼다.
    List<Blog> findAll();
    Blog findById(long blogId);
    void save(Blog blog);
    void deleteById(long blogId);

    //JPA에서는 .save()를 동일하게 쓰지만 현재코드에서 오버로딩도 불가능하고 ,
    //분리할 방법이 없으므로 메서드명을 다르게 사용한다.
//    void update(@Param("blogId") long blogId, @Param("blog") Blog blog);
    void update(Blog blog);

}
