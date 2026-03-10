package com.example.LearningManagementSystem.Notification;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService{

    @Override
    public void Notify(String message) {
        System.out.println("Email sent!!:"+message);
    }
}
