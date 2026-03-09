package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepo extends JpaRepository<StudentProfile,Long> {
}
