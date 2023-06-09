package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.web.dto.blog.BlogResponseDTO;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();
    Blog findById(long blogId);
    BlogResponseDTO findDTOById(long blogId);
    void deleteById(long blogId);
    void update(Blog blog);
    void updateById(long blogId, Blog blog);
//    void updateDTOById(long blogId, BlogSaveRequestDTO saveRequestDTO);
    void save(Blog blog);

}
