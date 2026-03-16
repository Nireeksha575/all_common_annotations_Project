package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Category;
import com.example.LearningManagementSystem.Entity.Course;
import com.example.LearningManagementSystem.Entity.Instructor;
import com.example.LearningManagementSystem.Exception.DuplicateResourceException;
import com.example.LearningManagementSystem.Exception.ResourceNotFoundException;
import com.example.LearningManagementSystem.Repository.CourseRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepo courseRepository;
    private final InstructorService instructorService;
    private final CategoryService categoryService;

    public CourseService(CourseRepo courseRepository,
                         InstructorService instructorService,
                         CategoryService categoryService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
        this.categoryService = categoryService;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", id));
    }

    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructor_InstructorId(instructorId);
    }

    public List<Course> getCoursesByCategory(Long categoryId) {
        return courseRepository.findByCategory_CategoryId(categoryId);
    }

    public List<Course> searchCourses(String keyword) {
        return courseRepository.findByCourseNameContainingIgnoreCase(keyword);
    }

    @Transactional
    public Course createCourse(Course course, Long instructorId, Long categoryId) {
        if (courseRepository.existsByCourseName(course.getCourseName())) {
            throw new DuplicateResourceException("Course with name '" + course.getCourseName() + "' already exists.");
        }
        Instructor instructor = instructorService.getInstructorById(instructorId);
        Category category = categoryService.getCategoryById(categoryId);
        course.setInstructor(instructor);
        course.setCategory(category);
        course.setCreated_at(LocalDateTime.now());
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Long id, Course updated, Long instructorId, Long categoryId) {
        Course existing = getCourseById(id);
        if (!existing.getCourseName().equals(updated.getCourseName())
                && courseRepository.existsByCourseName(updated.getCourseName())) {
            throw new DuplicateResourceException("Course with name '" + updated.getCourseName() + "' already exists.");
        }
        Instructor instructor = instructorService.getInstructorById(instructorId);
        Category category = categoryService.getCategoryById(categoryId);
        existing.setCourseName(updated.getCourseName());
        existing.setDescription(updated.getDescription());
        existing.setInstructor(instructor);
        existing.setCategory(category);
        return courseRepository.save(existing);
    }

    @Transactional
    public void deleteCourse(Long id) {
        getCourseById(id);
        courseRepository.deleteById(id);
    }
}