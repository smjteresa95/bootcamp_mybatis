package com.spring.blog.repository;

import com.spring.blog.entity.Reply;
import com.spring.blog.web.dto.reply.ReplyFindByIdDTO;
import com.spring.blog.web.dto.reply.ReplySaveDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyRepository {

    List<ReplyFindByIdDTO> findAllByBlogId(long blogId);
    void saveByBlodId(ReplySaveDTO replySaveDTO);

    //이걸로 가지고 온 정보를 토대로 수정할 수도, 삭제할 수도 있어야 한다.
    ReplyFindByIdDTO findByReplyId(int replyId);
    void deleteById(int replyId);
    void update(Reply reply);


}
