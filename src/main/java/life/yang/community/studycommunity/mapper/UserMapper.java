package life.yang.community.studycommunity.mapper;

import life.yang.community.studycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,create_at,modified_at) " +
            "values (#{accountId},#{name},#{token},#{createAt},#{modifiedAt})")
    void insert(User user);
}
