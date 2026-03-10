package com.example.LearningManagementSystem.Notification;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationService {
    @Override
    public void Notify(String message) {
        System.out.println("SMS sent!!:"+message);
    }
}
