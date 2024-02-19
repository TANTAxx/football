package hu.football.controllers;

import hu.football.exceptions.ValidationException;
import hu.football.models.dto.other.ApiError;
import hu.football.models.dto.other.ApiFieldError;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleMembershipException(RuntimeException ex) {
        ApiError error = new ApiError(LocalDateTime.now(), ex.getMessage());

        log.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleWrongCredentialException(RuntimeException ex) {
        ApiError error = new ApiError(LocalDateTime.now(), ex.getMessage());

        log.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiFieldError> handleClientNotValidException(ValidationException ex) {
        ApiFieldError error = new ApiFieldError();
//        error.setTimestamp(LocalDateTime.now());
//        error.setMessage(ex.getMessage());
        error.setValidationErrors(ex.getErrors());
        log.error(ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("{}", NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
        ex.printStackTrace();

        ApiError apiError = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .message(NestedExceptionUtils.getMostSpecificCause(ex).getMessage())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}


