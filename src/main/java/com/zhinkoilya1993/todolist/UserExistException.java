package com.zhinkoilya1993.todolist;

public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}