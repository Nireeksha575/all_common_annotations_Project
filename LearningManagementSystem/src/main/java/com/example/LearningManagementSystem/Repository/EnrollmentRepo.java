package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Long, Enrollment> {
}
