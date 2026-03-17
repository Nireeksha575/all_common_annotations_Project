package com.example.LearningManagementSystem.Event;

public class StudentCreatedEvent {
    private long studentId;

    public StudentCreatedEvent(long studentId) {
        this.studentId = studentId;
    }

    public long getStudentId() {
        return studentId;
    }
}
