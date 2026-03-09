package com.example.LearningManagementSystem.Exception;

public class ProfileAlreadySetException extends RuntimeException{
    public ProfileAlreadySetException(String msg){
        super(msg);
    }
}
