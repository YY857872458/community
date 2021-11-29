package life.yang.community.studycommunity.exception;

public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeException(String message) {
        super(message);
    }

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
