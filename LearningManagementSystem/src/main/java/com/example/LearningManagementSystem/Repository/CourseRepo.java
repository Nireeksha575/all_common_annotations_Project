package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByInstructor_InstructorId(Long instructorId);
    List<Course> findByCategory_CategoryId(Long categoryId);
    List<Course> findByCourseNameContainingIgnoreCase(String keyword);
    boolean existsByCourseName(String courseName);
    Optional<Course> findByCourseNameIgnoreCase(String courseName);
}
