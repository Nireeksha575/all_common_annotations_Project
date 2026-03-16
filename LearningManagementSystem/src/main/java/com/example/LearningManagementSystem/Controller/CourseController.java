package com.example.LearningManagementSystem.Controller;

import com.example.LearningManagementSystem.Entity.Course;
import com.example.LearningManagementSystem.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.getCoursesByInstructor(instructorId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(courseService.getCoursesByCategory(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @Valid @RequestBody Course course,
            @RequestParam Long instructorId,
            @RequestParam Long categoryId) {
        return new ResponseEntity<>(courseService.createCourse(course, instructorId, categoryId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course,
            @RequestParam Long instructorId,
            @RequestParam Long categoryId) {
        return ResponseEntity.ok(courseService.updateCourse(id, course, instructorId, categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}