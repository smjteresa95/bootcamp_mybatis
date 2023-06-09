package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.web.dto.blog.BlogResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogServiceImpl implements BlogService{
    BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository){
        this.blogRepository=blogRepository;
    }
    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(long blogId){
        return blogRepository.findById(blogId);
    }

    @Override
    public BlogResponseDTO findDTOById(long blogId) {
        return new BlogResponseDTO(blogRepository.findById(blogId));
    }

    @Override
    public void deleteById(long blogId) {
        blogRepository.deleteById(blogId);
    }

    @Override
    public void update(Blog blog) {
        blogRepository.update(blog);
    }

    @Override
    public void updateById(long blogId, Blog blog) {
        //수정할 블로그 가져오기
        //블로그 수정
        Blog blogToUpdate = Blog.builder()
                .blogId(blogId)
                .blogTitle(blog.getBlogTitle())
                .blogContent(blog.getBlogContent())
                .build();

        blogRepository.update(blogToUpdate);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

//    @Override
//    public void updateDTOById(long blogId, BlogSaveRequestDTO saveRequestDTO) {
//        //수정 할 블로그 가져오기
//        BlogSaveRequestDTO blogDTOToUpdate;
//        blogDTOToUpdate.toEntity().
//    }



}
