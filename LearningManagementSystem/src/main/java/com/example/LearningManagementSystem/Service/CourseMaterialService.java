package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Course;
import com.example.LearningManagementSystem.Entity.CourseMaterial;
import com.example.LearningManagementSystem.Exception.CourseNotFoundException;
import com.example.LearningManagementSystem.Repository.CourseMaterialRepo;
import com.example.LearningManagementSystem.Repository.CourseRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseMaterialService {

    private CourseMaterialRepo repo;
    private CourseRepo courseRepo;
    public CourseMaterialService(CourseMaterialRepo repo,CourseRepo courseRepo) {
        this.repo = repo;
        this.courseRepo=courseRepo;
    }

    public List<CourseMaterial> getAllMaterials(){
        return repo.findAll();
    }

    public CourseMaterial addMaterial(CourseMaterial material, String course_name){
        Course course=courseRepo.findByCourseNameIgnoreCase(course_name)
                .orElseThrow(()->new CourseNotFoundException("Course",course_name));
        material.setCourse(course);
       return repo.save(material);
    }

    public List<CourseMaterial> getMaterialById(long id){
        List<CourseMaterial> courseMaterials=repo.findByCourse_CourseId(id);
        return courseMaterials;
    }

    public void deleteMaterial(long id){
        Optional<CourseMaterial> courseMaterial=repo.findById(id);
        if(courseMaterial.isPresent()){
            repo.delete(courseMaterial.get());
        }
    }

    public ResponseEntity<List<?>> getMaterialCount() {
      return new ResponseEntity<>(repo.getMaterialCountPerCourse(), HttpStatus.OK);
    }
}
