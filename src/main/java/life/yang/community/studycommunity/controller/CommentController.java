package life.yang.community.studycommunity.controller;

import life.yang.community.studycommunity.dto.CommentDto;
import life.yang.community.studycommunity.dto.ResultDto;
import life.yang.community.studycommunity.exception.CustomizeErrorCode;
import life.yang.community.studycommunity.mapper.CommentMapper;
import life.yang.community.studycommunity.model.Comment;
import life.yang.community.studycommunity.model.User;
import life.yang.community.studycommunity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentService commentService;

    @PostMapping("/comment")
    public Object post(@RequestBody CommentDto commentDto,
                       HttpServletRequest request){
        final User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setParentId(commentDto.getParentId());
        comment.setType(commentDto.getType());
        comment.setAuthorId(user.getId());
        comment.setCreateAt(LocalDateTime.now());
        comment.setModifiedAt(LocalDateTime.now());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDto.okOf();
    }
}
