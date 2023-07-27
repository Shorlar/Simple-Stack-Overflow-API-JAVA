package com.stackOverflowAPI.MyFirstJavaProject.ExceptionHandler;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> validationException(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        String message = "";
        message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(","));
        errorMap.put("message", message);
        return  errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityExistsException.class)
    public Map<String,String> entityExistException(EntityExistsException ex){
        Map<String, String> errorMap = new HashMap<>();
         String message = ex.getMessage();
         errorMap.put("message", message);
        return  errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    public Map<String,String> databaseException(DatabaseException ex){
        Map<String, String> errorMap = new HashMap<>();
        String message = ex.getMessage();
        errorMap.put("message", message);
        return  errorMap;
    }
}
