package com.example.LearningManagementSystem.Exception;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException(String msg){
        super(msg);
    }
}
