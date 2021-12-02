package life.yang.community.studycommunity.controller;

import life.yang.community.studycommunity.dto.CommentResponseDto;
import life.yang.community.studycommunity.dto.QuestionDto;
import life.yang.community.studycommunity.service.CommentService;
import life.yang.community.studycommunity.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDto questionDto = questionService.findQuestionById(id);
        List<CommentResponseDto> comments = commentService.findByQuestionId(id);
        questionService.incView(questionDto.getId());
        model.addAttribute("question",questionDto);
        model.addAttribute("comments",comments);
        return "question";
    }
}
