package life.yang.community.studycommunity.service;

import life.yang.community.studycommunity.exception.CustomizeErrorCode;
import life.yang.community.studycommunity.exception.CustomizeException;
import life.yang.community.studycommunity.mapper.CommentMapper;
import life.yang.community.studycommunity.mapper.QuestionMapper;
import life.yang.community.studycommunity.model.Comment;
import life.yang.community.studycommunity.model.Question;
import life.yang.community.studycommunity.typeEnum.CommentTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final QuestionMapper questionMapper;

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
}
