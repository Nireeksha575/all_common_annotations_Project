package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Course;
import com.example.LearningManagementSystem.Entity.Enrollment;
import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Exception.AlreadyEnrolledException;
import com.example.LearningManagementSystem.Exception.ResourceNotFoundException;
import com.example.LearningManagementSystem.Repository.EnrollmentRepo;
import com.example.LearningManagementSystem.Repository.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrollmentService {

    private final EnrollmentRepo enrollmentRepository;
    private final StudentRepo studentRepository;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepo enrollmentRepository,
                             StudentRepo studentRepository,
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", id));
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudent_StudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourse_CourseId(courseId);
    }

    @Transactional
    public Enrollment enroll(Long studentId, Long courseId) {
        if (enrollmentRepository.existsByStudent_StudentIdAndCourse_CourseId(studentId, courseId)) {
            throw new AlreadyEnrolledException("Student " + studentId + " is already enrolled in course " + courseId);
        }
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));
        Course course = courseService.getCourseById(courseId);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledDate(LocalDateTime.now());
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unenroll(Long enrollmentId) {
        getEnrollmentById(enrollmentId);
        enrollmentRepository.deleteById(enrollmentId);
    }
}