package life.yang.community.studycommunity.mapper;

import life.yang.community.studycommunity.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    void insert(Comment comment);

    Comment findById(long id);
}
