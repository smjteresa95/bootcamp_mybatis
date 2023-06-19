package com.spring.blog.repository;

import com.spring.blog.web.dto.reply.ReplyFindByIdDTO;
import com.spring.blog.web.dto.reply.ReplyInsertDTO;
import com.spring.blog.web.dto.reply.ReplyUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyRepository {

    List<ReplyFindByIdDTO> findAllReplyByBlogId(long blogId);
    //이걸로 가지고 온 정보를 토대로 수정할 수도, 삭제할 수도 있어야 한다.
    ReplyFindByIdDTO findByReplyId(int replyId);
    void save(ReplyInsertDTO replyInsertDTO); //INSERT 구문을 완성시키기 위함
    void deleteByReplyId(int replyId);
    void update(ReplyUpdateDTO replyUpdateDTO);
}
