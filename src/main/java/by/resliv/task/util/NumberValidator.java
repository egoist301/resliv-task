package by.resliv.task.util;

import by.resliv.task.exception.*;

public final class NumberValidator {
    private NumberValidator() {
    }

    public static void validateId(Long id) {
        if (id < 0) {
            throw new IncorrectPathVariableException("Incorrect id (should be positive)");
        }
        if (id == 0) {
            throw new EntityIsNotExistException("object is not exist");
        }
    }
}
