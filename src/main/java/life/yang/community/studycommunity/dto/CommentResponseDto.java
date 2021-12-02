package life.yang.community.studycommunity.dto;

import life.yang.community.studycommunity.model.Comment;
import life.yang.community.studycommunity.model.User;
import lombok.Data;

@Data
public class CommentResponseDto extends Comment {
    private User user;
}
