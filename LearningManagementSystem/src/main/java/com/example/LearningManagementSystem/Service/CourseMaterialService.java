package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Course;
import com.example.LearningManagementSystem.Entity.CourseMaterial;
import com.example.LearningManagementSystem.Exception.CourseMaterialNotFoundException;
import com.example.LearningManagementSystem.Exception.CourseNotFoundException;
import com.example.LearningManagementSystem.Repository.CourseMaterialRepo;
import com.example.LearningManagementSystem.Repository.CourseRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseMaterialService {

    private final CourseMaterialRepo repo;
    private final CourseRepo courseRepo;

    public CourseMaterialService(CourseMaterialRepo repo, CourseRepo courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    public List<CourseMaterial> getAllMaterials() {
        return repo.findAll();
    }

    @Transactional
    public CourseMaterial addMaterial(CourseMaterial material, String course_name) {
        Course course = courseRepo.findByCourseNameIgnoreCase(course_name)
                .orElseThrow(() -> new CourseNotFoundException("Course", course_name));
        material.setCourse(course);
        return repo.save(material);
    }

    public List<CourseMaterial> getMaterialById(long id) {
        return repo.findByCourse_CourseId(id);
    }

    @Transactional
    public void deleteMaterial(long id) {
        // BUG FIX: was silently doing nothing if not found — now throws proper exception
        CourseMaterial courseMaterial = repo.findById(id)
                .orElseThrow(() -> new CourseMaterialNotFoundException("Course material with id:" + id + " not found"));
        repo.delete(courseMaterial);
    }

    public ResponseEntity<List<?>> getMaterialCount() {
        return new ResponseEntity<>(repo.getMaterialCountPerCourse(), HttpStatus.OK);
    }
}