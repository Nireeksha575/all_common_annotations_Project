package com.example.LearningManagementSystem.Controller;


import com.example.LearningManagementSystem.Entity.ErrorResponse;
import com.example.LearningManagementSystem.Exception.ProfileAlreadySetException;
import com.example.LearningManagementSystem.Exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handelStudentNotFoundException(StudentNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(),"Student Not Found", exception.getMessage()));

    }
    @ExceptionHandler(ProfileAlreadySetException.class)
    public ResponseEntity<ErrorResponse> handelProfileAlreadySetException(ProfileAlreadySetException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(),"Profile already set exception", exception.getMessage()));

    }
}
