package hu.football.exceptions;

import hu.football.model.dto.FieldError;

import java.util.List;

public class ValidationException extends RuntimeException {
    private List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }
}
