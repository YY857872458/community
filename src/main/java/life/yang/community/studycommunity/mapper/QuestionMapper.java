package life.yang.community.studycommunity.mapper;

import com.github.pagehelper.Page;
import life.yang.community.studycommunity.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void create(Question question);

    List<Question> list(@Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    Integer count();

    List<Question> listByUserId(@Param("userId") Long userId,
                                @Param("offset") Integer offset,
                                @Param("size") Integer size);

    Integer countById(@Param("userId") Long userId);

    Question findById(@Param("id") Long id);

    int update(Question question);

    Page<Question> findAll();

    List<Question> findByUserId(@Param("userId") Long userId);
}
