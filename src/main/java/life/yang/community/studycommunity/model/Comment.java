package life.yang.community.studycommunity.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long authorId;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;
}
