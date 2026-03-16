package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepo extends JpaRepository<StudentProfile,Long> {
}
