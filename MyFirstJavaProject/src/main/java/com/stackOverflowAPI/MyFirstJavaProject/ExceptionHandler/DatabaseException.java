package com.stackOverflowAPI.MyFirstJavaProject.ExceptionHandler;

public class DatabaseException extends RuntimeException {
    public DatabaseException(){
        super("An Error Occurred");
    }
}
