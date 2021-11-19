package life.yang.community.studycommunity.mapper;

import life.yang.community.studycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,create_at,modified_at,bio,avatar_url) " +
            "values (#{accountId},#{name},#{token},#{createAt},#{modifiedAt},#{bio},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where accountId = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name}, token = #{token}, modified_at=#{modifiedAt}, bio = #{bio} where id = #{id}")
    void update(User dbUser);
}
