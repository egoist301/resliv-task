package by.resliv.task.exception;

public class IncorrectPathVariableException extends RuntimeException {
    public IncorrectPathVariableException() {
    }

    public IncorrectPathVariableException(String message) {
        super(message);
    }

    public IncorrectPathVariableException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPathVariableException(Throwable cause) {
        super(cause);
    }
}
