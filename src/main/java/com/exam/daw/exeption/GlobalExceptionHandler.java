package com.exam.daw.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidateException(MethodArgumentNotValidException exception,
                                                      WebRequest request){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(e ->{
            errors.put(((FieldError)e).getField(),e.getDefaultMessage());
        });
        return new ResponseEntity<>(new ExceptionResponse(LocalDate.now(), errors.toString(), request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        return new ResponseEntity<>(new ExceptionResponse(LocalDate.now(), exception.getMessage(), request.getDescription(false)), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        String errorMessage = "Type enum incorrect";
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), errorMessage, request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
