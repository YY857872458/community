package life.yang.community.studycommunity.dto;

import life.yang.community.studycommunity.exception.CustomizeErrorCode;
import life.yang.community.studycommunity.exception.CustomizeException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

@Data
public class ResultDto {
    private Integer code;
    private String message;

    public static ResultDto errorOf(int code, String message) {
        final ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode){
        final ResultDto resultDto = new ResultDto();
        resultDto.setCode(errorCode.getCode());
        resultDto.setMessage(errorCode.getMessage());
        return resultDto;
    }

    public static ResultDto okOf() {
        final ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("成功");
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeException e) {
        return ResultDto.errorOf(e.getCode(),e.getMessage());
    }
}
