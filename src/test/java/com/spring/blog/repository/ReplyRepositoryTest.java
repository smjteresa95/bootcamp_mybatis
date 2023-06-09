package com.spring.blog.repository;

import com.spring.blog.web.dto.reply.ReplyFindByIdDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
public class ReplyRepositoryTest {

    //테스트 코드에서는 필드 주입을 해도 무방하다.
    @Autowired
    ReplyRepository repository;

    @Transactional
    @Test
    @DisplayName("2번 글에 연동 된 댓글 모두 보이기")
    public void findAllReplyByBlogIdTest(){
        //given
        long blogId = 2;
        //when
        List<ReplyFindByIdDTO> replyDTOList = repository.findAllByBlogId(blogId);
        //then
        assertThat(replyDTOList.size()).isEqualTo(4);
    }

    @Test
    @Transactional
    public void findByReplyId(){
        //given
        int replyId = 2;
        String expectedReplyContent = "댓글 1";
        //when
        ReplyFindByIdDTO replyDTO = repository.findByReplyId(replyId);
        //then
        assertThat(replyDTO.getReplyContent()).isEqualTo(expectedReplyContent);
    }



}
