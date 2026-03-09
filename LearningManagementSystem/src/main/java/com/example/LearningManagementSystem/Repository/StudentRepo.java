package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
