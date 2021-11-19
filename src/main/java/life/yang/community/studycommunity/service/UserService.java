package life.yang.community.studycommunity.service;

import life.yang.community.studycommunity.mapper.UserMapper;
import life.yang.community.studycommunity.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            user.setCreateAt(LocalDateTime.now());
            user.setModifiedAt(user.getCreateAt());
            userMapper.insert(user);
        } else {
            dbUser.setModifiedAt(LocalDateTime.now());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setBio(user.getBio());
            userMapper.update(dbUser);
        }
    }
}
