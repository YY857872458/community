package life.yang.community.studycommunity.mapper;

import life.yang.community.studycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    void insert(User user);

    User findByToken(String token);

    User findById(@Param("id") Long id);

    User findByAccountId(@Param("accountId") String accountId);

    void update(User dbUser);
}
