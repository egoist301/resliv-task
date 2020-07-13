package by.resliv.task.util;

import by.resliv.task.exception.EntityIsNotExistException;
import by.resliv.task.exception.IncorrectPathVariableException;

public class NumberValidator {
    public static void validateId(Long id) {
        if (id < 0) {
            throw new IncorrectPathVariableException("Incorrect id (should be positive)");
        }
        if (id == 0) {
            throw new EntityIsNotExistException("object is not exist");
        }
    }
}
