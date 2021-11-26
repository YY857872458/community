package life.yang.community.studycommunity.exception;

public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        super(message);
    }

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
