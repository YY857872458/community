package life.yang.community.studycommunity.advice;

import life.yang.community.studycommunity.dto.ResultDto;
import life.yang.community.studycommunity.exception.CustomizeErrorCode;
import life.yang.community.studycommunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    Object handle(Throwable ex, Model model, HttpServletRequest request) {
        final String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            //返回json
            if (ex instanceof CustomizeException) {
                return ResultDto.errorOf((CustomizeException) ex);
            } else {
                return ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
        } else {
            //错误页面跳转
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
