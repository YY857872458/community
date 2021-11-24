package life.yang.community.studycommunity.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String state;
    private String redirect_uri;
}
