package life.yang.community.studycommunity.controller;

import com.alibaba.fastjson.JSON;
import life.yang.community.studycommunity.dto.AccessTokenDto;
import life.yang.community.studycommunity.dto.GithubUser;
import life.yang.community.studycommunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                            @RequestParam(name = "state")String state){
        final AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setCode(code);
        accessTokenDto.setState(state);
        final String token = githubProvider.getAccessToken(accessTokenDto);
        final GithubUser user = githubProvider.getUser(token);
        System.out.println(JSON.toJSONString(user));
        return "index";
    }
}
