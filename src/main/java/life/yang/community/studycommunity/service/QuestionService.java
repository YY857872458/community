package life.yang.community.studycommunity.service;

import life.yang.community.studycommunity.dto.QuestionDto;
import life.yang.community.studycommunity.mapper.QuestionMapper;
import life.yang.community.studycommunity.mapper.UserMapper;
import life.yang.community.studycommunity.model.Question;
import life.yang.community.studycommunity.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;

    public List<QuestionDto> getAllQuestions() {
        final List<Question> questionList = questionMapper.getAllQuestions();
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (Question question : questionList) {
            final QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            User user = userMapper.findById(question.getCreator());
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }
}
