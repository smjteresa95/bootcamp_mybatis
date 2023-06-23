package com.spring.blog.repository;

import com.spring.blog.web.dto.reply.ReplyResponseDTO;
import com.spring.blog.web.dto.reply.ReplyCreateRequestDTO;
import com.spring.blog.web.dto.reply.ReplyUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyRepository {

    List<ReplyResponseDTO> findAllReplyByBlogId(long blogId);
    //이걸로 가지고 온 정보를 토대로 수정할 수도, 삭제할 수도 있어야 한다.
    ReplyResponseDTO findByReplyId(int replyId);
    void save(ReplyCreateRequestDTO replyCreateRequestDTO); //INSERT 구문을 완성시키기 위함
    void deleteByReplyId(int replyId);
    void update(ReplyUpdateRequestDTO replyUpdateRequestDTO);
    //블로그 Id를 받아서 특정 글과 연계된 댓글을 모두 삭제
    //대규모 서비스의 경우 성능상 실제로 FK를 걸지 않고, 논리적으로만 FK를 건 것 처럼 동작하게끔 한다.
    void deleteAllReplyByBlogId(long blogId);
}
