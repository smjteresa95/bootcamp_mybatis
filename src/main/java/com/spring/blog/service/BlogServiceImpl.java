package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogJPARepository;
import com.spring.blog.repository.BlogRepository;
import com.spring.blog.repository.ReplyRepository;
import com.spring.blog.web.dto.blog.BlogResponseDTO;
import com.spring.blog.web.dto.blog.BlogUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    BlogRepository blogRepository;
    //글을 하나 삭제 할 때 달려있는 댓글도 모두 지우기 위해.

    //mybatis로 repository layer를 구현한 것과, jpa로 repository구현 한 것 두 가지로 해서 구분 지어줌
    BlogJPARepository blogJPARepository;
    ReplyRepository replyRepository;

    public BlogServiceImpl(BlogJPARepository blogJPARepository, BlogRepository blogRepository, ReplyRepository replyRepository){
        this.blogJPARepository=blogJPARepository;
        this.blogRepository=blogRepository;
        this.replyRepository=replyRepository;
    }


    //고정된 pageNum으로 가공해주는 메서드
    public int getCalibratedPageNum(Long pageNum){
        //사용자가 음수를 넘겼거나 아무것도 넣지않는 경우
        if(pageNum == null || pageNum <= 0L){
            pageNum = 1L;
            return pageNum.intValue();
        } else {
            //총 페이지를 구하는 공식
            int totalPagesCount = (int) Math.ceil(blogJPARepository.count() / 10.0);
            pageNum = pageNum > totalPagesCount ? totalPagesCount : pageNum;
        }
        return pageNum.intValue();
    }


    @Override
    public Page<Blog> findAll(Long pageNum) {

        int calibratedPageNum = getCalibratedPageNum(pageNum);

        Pageable pageable = PageRequest.of(calibratedPageNum -1 ,10);
        return blogJPARepository.findAll(pageable);
    }

//    @Override
//    public List<Blog> findAll() {
////        return blogRepository.findAll();
//        return blogJPARepository.findAll();
//    }

    @Override
    public Blog findById(long blogId){
        Optional<Blog> blogOptional = blogJPARepository.findById(blogId);
        return blogOptional.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public BlogResponseDTO findDTOById(long blogId) {
        Optional<Blog> blogOptional = blogJPARepository.findById(blogId);
        assert blogOptional.orElse(null) != null;
        return new BlogResponseDTO(blogOptional.orElse(null));
    }

    @Override
    public void deleteById(long blogId) {
        //블로그 글에 연계된 댓글이 존재하는 지 확인 후 댓글 모두 삭제.
//        List<ReplyResponseDTO> replyList = replyRepository.findAllReplyByBlogId(blogId);
//        if(replyList.size()>0) replyRepository.deleteAllReplyByBlogId(blogId);
        blogJPARepository.deleteById(blogId);
    }

    @Override
    public void update(Blog blog) {
        Blog updatedBlog = blogJPARepository.findById(blog.getBlogId()).get();
        updatedBlog.setBlogTitle(blog.getBlogTitle());
        updatedBlog.setBlogContent(blog.getBlogContent());

        blogJPARepository.save(updatedBlog);
    }

//JPA 수정은 findById()를 이용해 얻어온 entity class를 수정 해 저장.

    public Blog updateDtoToEntity(BlogUpdateDTO blogUpdateDTO){
        return Blog.builder()
                .blogId(blogUpdateDTO.getBlogId())
                .writer(blogUpdateDTO.getWriter())
                .blogTitle(blogUpdateDTO.getBlogTitle())
                .blogContent(blogUpdateDTO.getBlogContent())
                .build();
    }

    public BlogUpdateDTO toUpdateDTO(Blog blog){
        BlogUpdateDTO blogUpdateDTO = new BlogUpdateDTO();
        blogUpdateDTO.setBlogId(blog.getBlogId());
        blogUpdateDTO.setWriter(blog.getWriter());
        blogUpdateDTO.setBlogTitle(blog.getBlogTitle());
        blogUpdateDTO.setBlogContent(blog.getBlogContent());
        return blogUpdateDTO;
    }
//    @Override
//    public void update(BlogUpdateDTO blogUpdateDTO){
//        List<Long> blogIdList = Collections.singletonList(blogUpdateDTO.getBlogId());
//        Iterable<Blog> responseDTO = blogJPARepository.findAllById(blogIdList);
//
//        for(Blog blog: responseDTO){
//            //entity -> dto
//            BlogUpdateDTO updatedDTO = toUpdateDTO(blog);
//
//            //modify title, content without using update method
//            updatedDTO.setBlogTitle(blogUpdateDTO.getBlogTitle());
//            updatedDTO.setBlogContent(blogUpdateDTO.getBlogContent());
//
//            //dto -> entity
//            Blog updatedBlog = updateDtoToEntity(updatedDTO);
//
//            //save modified object to DB
//            blogJPARepository.save(updatedBlog);
//        }
//
//    }

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
        blogJPARepository.save(blog);
    }

//    @Override
//    public void updateDTOById(long blogId, BlogSaveRequestDTO saveRequestDTO) {
//        //수정 할 블로그 가져오기
//        BlogSaveRequestDTO blogDTOToUpdate;
//        blogDTOToUpdate.toEntity().
//    }



}
