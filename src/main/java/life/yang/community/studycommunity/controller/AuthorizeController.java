package life.yang.community.studycommunity.controller;

import life.yang.community.studycommunity.dto.AccessTokenDto;
import life.yang.community.studycommunity.dto.GithubUser;
import life.yang.community.studycommunity.mapper.UserMapper;
import life.yang.community.studycommunity.model.User;
import life.yang.community.studycommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class AuthorizeController {

    private final GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    private final UserMapper userMapper;

    public AuthorizeController(GithubProvider githubProvider,UserMapper userMapper){
        this.githubProvider = githubProvider;
        this.userMapper = userMapper;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        final String token = githubProvider.getAccessToken(accessTokenDto);
        final GithubUser githubUser = githubProvider.getUser(token);
        if (githubUser != null) {
            final User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setCreateAt(LocalDateTime.now());
            user.setModifiedAt(user.getCreateAt());
            userMapper.insert(user);
            request.getSession().setAttribute("user", githubUser);
        }
        return "redirect:/";
    }
}
