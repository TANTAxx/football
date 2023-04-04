package hu.football.exceptions;

import hu.football.model.dto.FieldError;
import lombok.Data;

import java.util.List;


@Data
public class ValidationException extends RuntimeException {
    private List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        this.errors = errors;
    }
}
