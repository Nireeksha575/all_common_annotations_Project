package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.CourseMaterial;
import com.example.LearningManagementSystem.Projection.CourseMaterialCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepo extends JpaRepository<CourseMaterial, Long> {

    List<CourseMaterial> findByCourse_CourseId(long courseId);

    // BUG FIX: original query was missing "FROM" keyword — broke at runtime
    // Also @Modifying is not needed here (it's a SELECT), but added for any future UPDATE/DELETE queries
    @Query("SELECT m.course.courseName AS courseName, COUNT(m.id) AS materialCount " +
            "FROM CourseMaterial m GROUP BY m.course.courseName")
    List<CourseMaterialCount> getMaterialCountPerCourse();

    // Example of how @Modifying should be used — on any @Query that mutates data:
    // @Modifying
    // @Query("DELETE FROM CourseMaterial m WHERE m.course.courseId = :courseId")
    // void deleteAllByCourseId(@Param("courseId") Long courseId);
}