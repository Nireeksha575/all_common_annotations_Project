package com.example.LearningManagementSystem.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class AlreadyEnrolledException extends RuntimeException {
    public AlreadyEnrolledException(String message) {
        super(message);
    }
}
