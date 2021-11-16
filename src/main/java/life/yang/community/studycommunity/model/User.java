package life.yang.community.studycommunity.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String bio;
    private String avatarUrl;
}
