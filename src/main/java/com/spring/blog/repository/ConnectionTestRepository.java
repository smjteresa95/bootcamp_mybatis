package com.spring.blog.repository;

import org.apache.ibatis.annotations.Mapper;

//Bean container에 mybatis 에서 관리하는 파일로서 적재
@Mapper
public interface ConnectionTestRepository {

    //DB에 적용이 될 메서드만 인터페이스에 정의해준다.

    //getNow()실행 시 호출 할 SQL 구문은 xml파일 내부에 작성합니다.
    String getNow();

}
