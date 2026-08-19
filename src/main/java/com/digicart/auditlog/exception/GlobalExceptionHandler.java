package com.digicart.auditlog.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Maps domain and validation failures to HTTP error payloads.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle validation.
     *
     * @param ex ex
     * @return HTTP response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                e -> e.getField(),
                e -> e.getDefaultMessage(),
                (a, b) -> a
            ));
        return ResponseEntity.badRequest().body(Map.of("error", errors));
    }

    /**
     * Handle constraint.
     *
     * @param ex ex
     * @return HTTP response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraint(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    /**
     * Handle not found.
     *
     * @param ex ex
     * @return HTTP response
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(404).body(Map.of("error", ex.getMessage()));
    }

    /**
     * Handle bad arg.
     *
     * @param ex ex
     * @return HTTP response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadArg(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    /**
     * Handle generic.
     *
     * @param ex ex
     * @return HTTP response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.internalServerError().body(Map.of("error", "Internal server error"));
    }
}
