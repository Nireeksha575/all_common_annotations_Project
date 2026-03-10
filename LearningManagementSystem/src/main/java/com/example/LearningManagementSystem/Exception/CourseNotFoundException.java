package com.example.LearningManagementSystem.Exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message) {
        super(message);
    }
    public CourseNotFoundException(String resourceName,String name){
        super(resourceName + " not found with name: " + name);
    }
}
