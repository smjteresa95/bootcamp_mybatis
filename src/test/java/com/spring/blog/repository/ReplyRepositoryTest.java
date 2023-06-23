package com.spring.blog.repository;

import com.spring.blog.web.dto.reply.ReplyResponseDTO;
import com.spring.blog.web.dto.reply.ReplyCreateRequestDTO;
import com.spring.blog.web.dto.reply.ReplyUpdateRequestDTO;
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
        List<ReplyResponseDTO> replyDTOList = repository.findAllReplyByBlogId(blogId);
        //then
        assertThat(replyDTOList.size()).isEqualTo(4);
    }

    @Test
    @Transactional
    public void findByReplyId(){
        //given
        int replyId = 2;
        String expectedReplyContent = "댓글2";
        //when
        ReplyResponseDTO replyDTO = repository.findByReplyId(replyId);
        //then
        assertThat(replyDTO.getReplyContent()).isEqualTo(expectedReplyContent);
    }

    @Test
    @Transactional
    @DisplayName("글번호 2번에 연동된 댓글번호 2번을 삭제 후 전체 데이터 갯수가 4개이고, 2번으로 재조회시 null일 것이다.")
    public void deleteByReplyIdTest(){
        long blogId=2;
        int replyId=2;
        repository.deleteByReplyId(replyId);
        assertThat(repository.findAllReplyByBlogId(blogId).size()).isEqualTo(3);
        assertThat(repository.findByReplyId(replyId)).isEqualTo(null);
    }

    @Test
    @Transactional
    @DisplayName("fixture이용해 INSERT 후, 전체 데이터를 가져와 마지막 인덱스 번호 요소를 얻은 후, 입력했던 fixture과 비교")
    public void saveReplyTest(){
        //given
        long blogId=2;
        String replyWriter = "replyWriter";
        String replyContent = "replyContent";
        ReplyCreateRequestDTO insertDTO = ReplyCreateRequestDTO.builder()
                .blogId(blogId).replyWriter(replyWriter).replyContent(replyContent).build();

        //when
        repository.save(insertDTO);

        //then
        //3번 블로그에 달린 댓글을 리스트로 가져오기
        List<ReplyResponseDTO> replyFoundByBlogIdList = repository.findAllReplyByBlogId(blogId);
        //가장 마지막에 달린 댓글 넣을 객체를 null로 초기화.

        //replyId가 가장 큰 값을 찾아서 lastestReply로 대입.
        ReplyResponseDTO latestReply = null;
        for(ReplyResponseDTO reply:  replyFoundByBlogIdList){
            //replyId 값이 0보다 커야 한다.
            if(reply.getReplyId() > 0 && latestReply == null || reply.getReplyId() > latestReply.getReplyId())
                latestReply = reply;
        }
//        ReplyFindByIdDTO lastReply = null;
//
//        for (ReplyFindByIdDTO reply : replyFoundByBlogIdList) {
//            //lastReply에 가장 높은 id 값을 가진 reply를 대입한다.
//            //댓글의 id가 0보다 크고 && (lastReply가 null이거나(댓글이 할당되지 않은 경우) || list에 있던 replyId 가 lastReply 의 id 보다 큰 경우)
//            if (reply.getReplyId() > 0 && (lastReply == null || reply.getReplyId() > lastReply.getReplyId())) {
//                lastReply = reply;
//            }
//        }

        assertThat(latestReply.getReplyContent()).isEqualTo(replyContent);
        assertThat(repository.findAllReplyByBlogId(blogId).size()).isEqualTo(5);
    }

    @Test
    @Transactional
    public void updateReplyTest(){
        int replyId=3;
        String writer = "writer";
        String updatedReplyContent = "updatedReplyContent";

        //업데이트 할 댓글 가져오기
        ReplyResponseDTO replyFoundByReplyId = repository.findByReplyId(replyId);

        //가지고 온 댓글을 업데이트 할 수 있는 DTO에 대입 후 수정
        ReplyUpdateRequestDTO updateDTO = new ReplyUpdateRequestDTO(replyFoundByReplyId);
        updateDTO = ReplyUpdateRequestDTO.builder()
                .replyId(replyId).replyWriter(writer).replyContent(updatedReplyContent).build();

        repository.update(updateDTO);

        ReplyResponseDTO result = repository.findByReplyId(replyId);
        assertThat(result.getReplyWriter()).isEqualTo(writer);
        assertThat(result.getReplyContent()).isEqualTo(updatedReplyContent);
        assertThat(result.getUpdatedAt().isAfter(result.getPublishedAt()));
    }

    @Test
    @Transactional
    public void deleteAllReplyByBlogIdTest(){
        long blogId = 2;

        repository.deleteAllReplyByBlogId(blogId);

        List<ReplyResponseDTO> replyList = repository.findAllReplyByBlogId(blogId);
        assertThat(replyList.size()).isEqualTo(0);
    }

}
