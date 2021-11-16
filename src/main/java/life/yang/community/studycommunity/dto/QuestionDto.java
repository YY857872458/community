package life.yang.community.studycommunity.dto;

import life.yang.community.studycommunity.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long creator;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private String tag;
    private User user;
}
