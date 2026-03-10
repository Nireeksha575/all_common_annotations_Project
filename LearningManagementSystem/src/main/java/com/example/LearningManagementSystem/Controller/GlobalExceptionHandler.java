package com.example.LearningManagementSystem.Controller;


import com.example.LearningManagementSystem.Entity.ErrorResponse;
import com.example.LearningManagementSystem.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Map<String, Object> buildError(HttpStatus status, String message, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return body;
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handelStudentNotFoundException(StudentNotFoundException exception,WebRequest request){
        return new ResponseEntity<>(buildError(HttpStatus.NOT_FOUND,
                exception.getMessage(),request),HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ProfileAlreadySetException.class)
    public ResponseEntity<Map<String, Object>> handelProfileAlreadySetException(ProfileAlreadySetException exception,WebRequest request){
        return new ResponseEntity<>(buildError(HttpStatus.BAD_REQUEST,
                exception.getMessage(),request),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handelProfileNotFoundException(ProfileNotFoundException exception,WebRequest request){
        return new ResponseEntity<>(buildError(HttpStatus.NOT_FOUND,
                exception.getMessage(),request),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseMaterialNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handelCourseMaterialNotFoundException(
            CourseMaterialNotFoundException exception,
            WebRequest request
    ){
        return new ResponseEntity<>(buildError(HttpStatus.NOT_FOUND,
                exception.getMessage(),request),HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handelCourseNotFoundException(CourseNotFoundException exception,WebRequest request){
        return new ResponseEntity<>(buildError(HttpStatus.NOT_FOUND,exception.getMessage(),request),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(
            DuplicateResourceException ex, WebRequest request) {
        return new ResponseEntity<>(buildError(HttpStatus.CONFLICT, ex.getMessage(), request), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyEnrolledException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyEnrolled(
            AlreadyEnrolledException ex, WebRequest request) {
        return new ResponseEntity<>(buildError(HttpStatus.CONFLICT, ex.getMessage(), request), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.", request), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}