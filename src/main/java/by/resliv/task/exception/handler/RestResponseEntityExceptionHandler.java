package by.resliv.task.exception.handler;

import by.resliv.task.exception.EntityIsAlreadyExistException;
import by.resliv.task.exception.EntityIsNotExistException;
import by.resliv.task.exception.IncorrectPathVariableException;
import by.resliv.task.exception.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;

@ControllerAdvice(annotations = RestController.class)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return createResponse(new RuntimeException(ex.getBindingResult().getFieldError().getDefaultMessage(), ex),
                              status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handlePathException(RuntimeException e) {
        return createResponse(new RuntimeException("incorrect path URI", e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityIsNotExistException.class, NoResultException.class})
    public ResponseEntity<Object> handleNotExistException(RuntimeException e) {
        return createResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPathVariableException.class)
    public ResponseEntity<Object> handlePathVariableException(RuntimeException e) {
        return createResponse(e, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EntityIsAlreadyExistException.class)
    public ResponseEntity<Object> handleAlreadyExistException(RuntimeException e) {
        return createResponse(e, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> createResponse(Exception ex, HttpStatus status) {
        ErrorDto error = new ErrorDto();
        error.setStatus(status);
        error.setCode(status.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), status);
    }
}

