package com.spring.blog.exception;

//RuntimeException을 상속받아 unchecked excpetion으로 만들어준다.
public class NotFoundBlogIdException extends RuntimeException {
    //생성자에 에러사유를 전달 할 수 있도록 메세지를 적습니다.
    public NotFoundBlogIdException(String message){
        super(message);
    }
}
