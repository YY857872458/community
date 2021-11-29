package life.yang.community.studycommunity;

import life.yang.community.studycommunity.controller.CommentController;
import life.yang.community.studycommunity.dto.CommentDto;
import life.yang.community.studycommunity.mapper.CommentMapper;
import life.yang.community.studycommunity.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    CommentController commentController;

//    @Test
//    void test_comment_insert(){
//        final CommentDto commentDto = new CommentDto();
//        commentDto.setParentId(1L);
//        commentDto.setContent("test");
//        commentDto.setType(1);
//        commentController.post(commentDto);
//
//        final Comment comment = commentMapper.findById(1L);
//        System.out.println(comment);
//        Assertions.assertEquals(1,comment.getAuthorId());
//    }
}
