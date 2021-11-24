package life.yang.community.studycommunity.service;

import com.github.pagehelper.PageHelper;
import life.yang.community.studycommunity.dto.PaginationDto;
import life.yang.community.studycommunity.dto.QuestionDto;
import life.yang.community.studycommunity.mapper.QuestionMapper;
import life.yang.community.studycommunity.mapper.UserMapper;
import life.yang.community.studycommunity.model.Question;
import life.yang.community.studycommunity.model.User;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;

    public PaginationDto getQuestionList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final List<Question> allQuestions = questionMapper.findAll();
        final Integer totalCount = questionMapper.count();
        return getPaginationDto(pageNum, pageSize, allQuestions, totalCount);
    }

    @NotNull
    private PaginationDto getPaginationDto(Integer pageNum, Integer pageSize, List<Question> questions, Integer count) {
        final PaginationDto paginationDto = new PaginationDto();
        int maxPage = getMaxPage(pageSize, count);
        pageNum = handleIllegalPageNum(pageNum, maxPage);
        paginationDto.setPagination(pageNum, maxPage);
        List<QuestionDto> questionDtoList = new ArrayList<>();
        questions.stream().map(this::buildQuestionDto).forEach(questionDtoList::add);
        paginationDto.setQuestions(questionDtoList);
        return paginationDto;
    }

    private int getMaxPage(Integer pageSize, Integer count) {
        return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }

    @NotNull
    private Integer handleIllegalPageNum(Integer pageNum, int maxPage) {
        return (pageNum < 1) ? 1 : pageNum > maxPage ? maxPage : pageNum;
    }

    @NotNull
    private QuestionDto buildQuestionDto(Question question) {
        final User user = userMapper.findById(question.getCreator());
        final QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(user);
        return questionDto;
    }

    public PaginationDto list(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        final List<Question> userQuestion = questionMapper.findByUserId(userId);
        final Integer totalCount = questionMapper.countById(userId);
        return getPaginationDto(pageNum, pageSize, userQuestion, totalCount);
    }

    public QuestionDto findQuestionById(Long id) {
        Question question = questionMapper.findById(id);
        final QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        final User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {
        final Long id = question.getId();
        if (id == null) {
            question.setCreateAt(LocalDateTime.now());
            question.setModifiedAt(question.getCreateAt());
            question.setCommentCount(0L);
            question.setLikeCount(0L);
            question.setViewCount(0L);
            questionMapper.create(question);
        } else {
            final Question dbQuestion = questionMapper.findById(id);
            dbQuestion.setModifiedAt(LocalDateTime.now());
            dbQuestion.setTitle(question.getTitle());
            dbQuestion.setDescription(question.getDescription());
            dbQuestion.setTag(question.getTag());
            questionMapper.update(dbQuestion
            );
        }
    }
}
