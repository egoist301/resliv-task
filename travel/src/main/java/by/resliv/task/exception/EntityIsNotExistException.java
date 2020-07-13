package by.resliv.task.exception;

public class EntityIsNotExistException extends RuntimeException {
    public EntityIsNotExistException() {
    }

    public EntityIsNotExistException(String message) {
        super(message);
    }

    public EntityIsNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIsNotExistException(Throwable cause) {
        super(cause);
    }
}
