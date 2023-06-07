package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.web.dto.BlogResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
public class BlogServiceTest {
    //테스트 코드니까 field주입해도 된다.
    @Autowired
    BlogService blogService;

    @Test @Transactional //이 테스트의 결과가 DB commit을 하지 않는다.
    public void findAllTest(){
        //given
        //when
        List<Blog> blogList = blogService.findAll();
        //then
        assertThat(blogList.size())
                .isEqualTo(3);
    }

    @Test @Transactional
    public void findByIdTest(){
        //given
        long blogId = 2;
        String expectedWriter = "2번유저";
        String expectedBlogTitle = "2번제목";
        //when
        Blog blog = blogService.findById(blogId);
        //then
        assertThat(blog.getWriter()).isEqualTo(expectedWriter);
        assertThat(blog.getBlogTitle()).isEqualTo(expectedBlogTitle);
    }

    @Test @Transactional
    @DisplayName("findById method using DTO")
    public void findDTOByIdTest(){
        //given
        long blogId = 2;
        String expectedWriter = "2번유저";
        String expectedBlogTitle = "2번제목";

        //when
        BlogResponseDTO responseDTO = blogService.findDTOById(blogId);

        //then
        assertThat(responseDTO.getWriter()).isEqualTo(expectedWriter);
        assertThat(responseDTO.getBlogTitle()).isEqualTo(expectedBlogTitle);
    }

    //DB행 갯수를 직접 입력하지 않고 지우기 전 값과 후의 값을 계산해 비교해도 괜찮은가요?
    @Test @Transactional
    public void deleteByIdTest(){
        //given
        long blogId = 2;
        List<Blog> blogList = blogService.findAll();
        int blogListSize = blogList.size();
        //when
        blogService.deleteById(blogId);
        //then
        List<Blog> updatedBlogList = blogService.findAll();
        assertThat(updatedBlogList.size()).isEqualTo(blogListSize-1);
    }

    @Test @Transactional
    public void updateTest(){
        //given
        long blogId = 2;
        String expectedTitle = "updated title";
        String expectedContent = "updated content";

        Blog blogToUpdate = Blog.builder()
                .blogId(blogId)
                .blogTitle(expectedTitle)
                .blogContent(expectedContent)
                .build();

        //when
        blogService.update(blogToUpdate);

        //then
        assertThat(blogService.findById(blogId).getBlogTitle()).isEqualTo(expectedTitle);
        assertThat(blogService.findById(blogId).getBlogContent()).isEqualTo(expectedContent);
    }
    @Test @Transactional
    public void updateByIdTest(){
        //given
        long blogId = 2;
        String expectedTitle = "updated title";
        String expectedContent = "updated content";

        Blog blog = blogService.findById(blogId);
        blog.setBlogTitle(expectedTitle);
        blog.setBlogContent(expectedContent);

        //when
        blogService.updateById(blogId, blog);

        //then
        assertThat(blogService.findById(blogId).getBlogTitle()).isEqualTo(expectedTitle);
        assertThat(blogService.findById(blogId).getBlogContent()).isEqualTo(expectedContent);
    }

    @Test @Transactional
    public void save(){
        //given
        String writer = "writer";
        String title = "title";
        String content = "content";


        Blog newBlog = Blog.builder()
                .writer(writer).blogTitle(title).blogContent(content)
                .build();

        //when
        blogService.save(newBlog);

        //Then
        List<Blog> blog = blogService.findAll();
        System.out.println(newBlog);
        assertThat(blog.size()).isEqualTo(4);
    }

}
