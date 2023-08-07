package com.stackOverflowAPI.MyFirstJavaProject.ExceptionHandler;

import lombok.NoArgsConstructor;

public class DatabaseException extends RuntimeException {
    public DatabaseException(){
        super("An Error Occurred");
    }

    public DatabaseException(String message, Exception e){
        super(message, e.getCause());
    }
}
