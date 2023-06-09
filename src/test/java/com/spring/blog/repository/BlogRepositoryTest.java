package com.spring.blog.repository;

import com.spring.blog.entity.Blog;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
//@AfterAll 을 써야 하는 경우에 필요하고 @AfterEach를 쓰고 싶으면 PER_METHOD로 고쳐준다.
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BlogRepositoryTest {
    @Autowired
    BlogRepository blogRepository;

    @BeforeEach //각 테스트 전에 공통적으로 실행 할 코드를 저장해두는 곳
    public void setBlogTable(){
        blogRepository.createBlogTable(); //blog 테이블 생성
        blogRepository.insertTestData();  //생성된 blog 테이블에 더미데이터 3개 입력
    }

    @Test
    public void findAllTest(){
        //given 사람기준 2번 요소 조회를 위한 fixture 선언
        int blogId = 1;

        //when DB에 있는 모든 데이터를 자바 엔터티로 역직렬화
        List<Blog> blogList = blogRepository.findAll();

        //then: Dummy data 가 3개 이므로, 3개일 것이라 단언
        assertThat(blogList.size()).isEqualTo(3);
//        assertEquals(3, blogList.size());

        //2번 객체의 ID 번호는 2번일 것이다.
    }

    @Test
    public void findByIdTest(){
        //given
        long blogId = 2;
        //when
        Blog blog = blogRepository.findById(blogId);
        //Then

        assertThat(blog.getWriter()).isEqualTo("user no.2");
        assertThat(blog.getBlogTitle()).isEqualTo("title2");
        assertThat(blog.getBlogId()).isEqualTo(2);
    }

    @Test
    public void saveTest(){
        //given

        String writer = "저자";
        String blogTitle = "제목";
        String blogContent = "내용";

//        Blog newBlog = new Blog();
//        newBlog.setWriter(writer);
//        newBlog.setBlogTitle(blogTitle);
//        newBlog.setBlogContent(blogContent);

        Blog newBlog = Blog.builder()
                .writer(writer).blogTitle(blogTitle).blogContent(blogContent)
                .build();

        //when
        blogRepository.save(newBlog);
        int blogId = 3;
        List<Blog> blogList = blogRepository.findAll();

        //then
        assertThat(blogList.size()).isEqualTo(4);
        assertThat(blogList.get(blogId).getWriter()).isEqualTo(writer);
        assertThat(blogList.get(blogId).getBlogTitle()).isEqualTo(blogTitle);
        assertThat(blogList.get(blogId).getBlogContent()).isEqualTo(blogContent);

    }

    @Test
    public void deleteTest(){
        //given
        long blogId = 2;
        //when
        blogRepository.deleteById(blogId);
        //then
        List<Blog> blogList = blogRepository.findAll();

        assertThat(blogList.size()).isEqualTo(2);
    }

    @Test
    public void updateTest(){
        //given
        long blogId = 2;
        String title = "updated title";
        String content = "updated content";

//        //원본 블로그
//        Blog blogToUpdate = blogRepository.findById(blogId);
//        //수정 된 블로그
//
//        blogToUpdate.setBlogTitle(title);
//        blogToUpdate.setBlogContent(content);

        Blog blogToUpdate = Blog.builder()
                .blogId(blogId).blogTitle(title).blogContent(content)
                .build();

        //when
        blogRepository.update(blogToUpdate);

        //then
        assertThat(blogRepository.findById(blogId).getBlogTitle())
                .isEqualTo(title);

        assertThat(blogRepository.findById(blogId).getBlogContent())
                .isEqualTo(content);
    }

    @AfterEach //각 단위테스트 끝난 후에 실행 할 구문을 작성
    public void dropBlogTable(){
        blogRepository.dropBlogTable();
    }
}
