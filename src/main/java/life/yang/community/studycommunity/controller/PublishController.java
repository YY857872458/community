package life.yang.community.studycommunity.controller;

import life.yang.community.studycommunity.dto.QuestionDto;
import life.yang.community.studycommunity.mapper.QuestionMapper;
import life.yang.community.studycommunity.mapper.UserMapper;
import life.yang.community.studycommunity.model.Question;
import life.yang.community.studycommunity.model.User;
import life.yang.community.studycommunity.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequestMapping("/publish")
@RequiredArgsConstructor
public class PublishController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long questionId,
                       Model model){
        final QuestionDto question = questionService.findQuestionById(questionId);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id",questionId);
        return "publish";
    }

    @GetMapping
    public String publish() {
        return "publish";
    }

    @PostMapping
    public String doPublish(@RequestParam(name = "title") String title,
                            @RequestParam(name = "description") String description,
                            @RequestParam(name = "tag") String tag,
                            @RequestParam(name = "id", required = false) Long id,
                            HttpServletRequest request,
                            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登陆");
            return "publish";
        }

        if (title == null || title.equals("")) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        final Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(Objects.requireNonNull(user).getId());
        question.setId(id);
        questionService.createOrUpdate(question);
//        questionMapper.create(question);
        return "redirect:/";
    }
}
