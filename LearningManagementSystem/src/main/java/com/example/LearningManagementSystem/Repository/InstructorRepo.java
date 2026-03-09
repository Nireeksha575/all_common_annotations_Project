package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Long, Instructor> {
}
