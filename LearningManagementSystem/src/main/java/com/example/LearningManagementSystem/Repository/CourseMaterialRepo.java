package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.CourseMaterial;
import com.example.LearningManagementSystem.Projection.CourseMaterialCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseMaterialRepo extends JpaRepository<CourseMaterial,Long> {

    List<CourseMaterial> findByCourse_CourseId(long courseId);
    @Query("select m.course.courseName as courseName,count(m.id) as materialCount from CourseMaterial m group by m.course.courseName")
    List<CourseMaterialCount> getMaterialCountPerCourse();
}
