package com.example.LearningManagementSystem.Listeners;

import com.example.LearningManagementSystem.Event.StudentCreatedEvent;
import com.example.LearningManagementSystem.Notification.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class StudentNotificationListener {
    private final NotificationService notificationService;

    public StudentNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleStudentCreatedEvent(StudentCreatedEvent event){
        notificationService.Notify("Student account created for Student Id:"+event.getStudentId());
    }
}
