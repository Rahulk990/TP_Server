package com.flock.TP_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException(Exception ex, WebRequest request) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Invalid value(s) in request");
    }

    @ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage duplicateEntryException(DuplicateEntryException ex, WebRequest request) {
        return new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getMessage());
    }
}
