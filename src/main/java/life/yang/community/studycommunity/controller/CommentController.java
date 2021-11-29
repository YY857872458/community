package life.yang.community.studycommunity.controller;

import life.yang.community.studycommunity.dto.CommentDto;
import life.yang.community.studycommunity.mapper.CommentMapper;
import life.yang.community.studycommunity.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentMapper commentMapper;

    @PostMapping("/comment")
    public Object post(@RequestBody CommentDto commentDto){
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setParentId(commentDto.getParentId());
        comment.setType(commentDto.getType());
        comment.setAuthorId(1L);
        comment.setCreateAt(LocalDateTime.now());
        comment.setModifiedAt(LocalDateTime.now());
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        return null;
    }
}
