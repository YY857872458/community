package life.yang.community.studycommunity.exception;

public enum CustomizeErrorCode implements IErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不存在，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "用户未登陆"),
    SYS_ERROR(2004,"服务器冒烟啦，要不过一会再试试？"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，要不换一个试试？");

    private final String message;
    private final Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
