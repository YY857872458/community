package life.yang.community.studycommunity.exception;

public enum CustomizeErrorCode implements IErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在，要不换个试试？");

    private final String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
