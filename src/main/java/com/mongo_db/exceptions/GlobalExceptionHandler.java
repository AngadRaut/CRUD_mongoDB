package com.mongo_db.exceptions;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<?> handlePersonNotFoundException() {
        log.error("Resource with the given identifier was not found.");
        log.warn("Ensure the provided details are correct.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found. Please check your request details.");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("Provide the valid details!{}", exception.getConstraintViolations());
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please provide valid information!\n" + constraintViolations);
    }
}