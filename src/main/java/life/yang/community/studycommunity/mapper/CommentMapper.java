package life.yang.community.studycommunity.mapper;

import life.yang.community.studycommunity.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    void insert(Comment comment);

    Comment findById(long id);

    List<Comment> findByQuestionId(@Param("questionId") Long questionId);
}
