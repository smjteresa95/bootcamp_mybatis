package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.web.dto.blog.BlogResponseDTO;
import com.spring.blog.web.dto.blog.BlogUpdateDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {
    Page<Blog> findAll(Long pageNum);
//    List<Blog> findAll();
    Blog findById(long blogId);
    BlogResponseDTO findDTOById(long blogId);
    void deleteById(long blogId);
    void update(Blog blog);
//    void update(BlogUpdateDTO blogUpdateDTO);

    void updateById(long blogId, Blog blog);
//    void updateDTOById(long blogId, BlogSaveRequestDTO saveRequestDTO);
    void save(Blog blog);

}
