package com.example.todo_list.exceptions;

import java.lang.RuntimeException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
