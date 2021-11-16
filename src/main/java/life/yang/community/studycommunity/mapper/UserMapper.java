package life.yang.community.studycommunity.mapper;

import life.yang.community.studycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,create_at,modified_at,bio,avatar_url) " +
            "values (#{accountId},#{name},#{token},#{createAt},#{modifiedAt},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
