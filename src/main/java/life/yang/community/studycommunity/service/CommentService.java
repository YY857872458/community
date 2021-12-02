package life.yang.community.studycommunity.service;

import life.yang.community.studycommunity.dto.CommentResponseDto;
import life.yang.community.studycommunity.exception.CustomizeErrorCode;
import life.yang.community.studycommunity.exception.CustomizeException;
import life.yang.community.studycommunity.mapper.CommentMapper;
import life.yang.community.studycommunity.mapper.QuestionMapper;
import life.yang.community.studycommunity.mapper.UserMapper;
import life.yang.community.studycommunity.model.Comment;
import life.yang.community.studycommunity.model.Question;
import life.yang.community.studycommunity.model.User;
import life.yang.community.studycommunity.typeEnum.CommentTypeEnum;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;

    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
            //回复评论
            final Comment dbComment = commentMapper.findById(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复问题
            final Question question = questionMapper.findById(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.incCommentCount(comment.getParentId());
        }
    }

    public List<CommentResponseDto> findByQuestionId(Long questionId) {
        final List<Comment> allComments = commentMapper.findByQuestionId(questionId);
        final List<Comment> questionComments = allComments.stream().filter(this::isLevel1).collect(Collectors.toList());
        if (questionComments.size() == 0) {
            return Collections.emptyList();
        }
        return questionComments.stream().map(this::getCommentResponseDto).collect(Collectors.toList());
    }

    private boolean isLevel1(Comment comment) {
        return comment.getType() == 1;
    }

    private CommentResponseDto getCommentResponseDto(Comment comment) {
        final Long authorId = comment.getAuthorId();
        final User commentator = userMapper.findById(authorId);
        final CommentResponseDto response = new CommentResponseDto();
        BeanUtils.copyProperties(comment, response);
        response.setUser(commentator);
        return response;
    }
}
