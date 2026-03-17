package com.example.LearningManagementSystem.Service;



import com.example.LearningManagementSystem.DTO.StudentProfileDTO;
import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Entity.StudentProfile;
import com.example.LearningManagementSystem.Exception.StudentNotFoundException;
import com.example.LearningManagementSystem.Notification.NotificationService;
import com.example.LearningManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepo studentRepo;
    private final NotificationService notificationService;

    public StudentService(StudentRepo studentRepo,
                          @Qualifier("emailNotificationService") NotificationService notificationService) {
        this.studentRepo = studentRepo;
        this.notificationService = notificationService;
    }

    public List<StudentProfileDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        List<StudentProfileDTO> studentProfileDTOS = new ArrayList<>();
        for (Student student : students) {
            studentProfileDTOS.add(convertToStudentDTO(student));
        }
        return studentProfileDTOS;
    }

    @Cacheable(value = "Students",key = "#id")
    public StudentProfileDTO getStudentById(long id){
        Student student=studentRepo.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student with id:" + id + " not found"));
        return convertToStudentDTO(student);
    }

    @Transactional
    @CacheEvict(value = "Students",key = "#id")
    public StudentProfileDTO updateStudent(long id, Student updateStudent) {
        var student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id:" + id + " not found"));

        if (updateStudent.getName() != null) student.setName(updateStudent.getName());
        if (updateStudent.getEmail() != null) student.setEmail(updateStudent.getEmail());
        if (updateStudent.getPassword() != null) student.setPassword(updateStudent.getPassword());
        if (updateStudent.getPhoneNumber() != null) student.setPhoneNumber(updateStudent.getPhoneNumber());

        if (updateStudent.getProfile() != null) {
            StudentProfile existingProfile = student.getProfile();
            if (existingProfile == null) {
                existingProfile = new StudentProfile();
                existingProfile.setStudent(student);
                student.setProfile(existingProfile);
            }
            StudentProfile incoming = updateStudent.getProfile();
            if (incoming.getAddress() != null) existingProfile.setAddress(incoming.getAddress());
            if (incoming.getDateOfBirth() != null) existingProfile.setDateOfBirth(incoming.getDateOfBirth());
            if (incoming.getEducation() != null) existingProfile.setEducation(incoming.getEducation());
        }
        Student savedStudent=studentRepo.save(student);
        return convertToStudentDTO(savedStudent);
    }

    private StudentProfileDTO convertToStudentDTO(Student student) {
        StudentProfileDTO studentProfileDTO = new StudentProfileDTO();
        studentProfileDTO.setStudentId(student.getStudentId());
        studentProfileDTO.setName(student.getName());
        studentProfileDTO.setEmail(student.getEmail());
        studentProfileDTO.setPhoneNumber(student.getPhoneNumber());
        if (student.getProfile() != null) {
            studentProfileDTO.setAddress(student.getProfile().getAddress());
            studentProfileDTO.setDateOfBirth(student.getProfile().getDateOfBirth());
            studentProfileDTO.setEducation(student.getProfile().getEducation());
        }
        return studentProfileDTO;
    }

    @Transactional
    public StudentProfileDTO addStudent(Student student) {
        StudentProfile studentProfile = student.getProfile();
        if (studentProfile != null) {
            notificationService.Notify("Profile created for id:"+student.getStudentId());
            studentProfile.setStudent(student);
            student.setProfile(studentProfile);
        }
        Student savedStudent = studentRepo.save(student);
        return convertToStudentDTO(savedStudent);
    }

    @Transactional
    @CacheEvict(value = "Students",key = "#id")
    public void deleteStudent(long id) {
        // BUG FIX: was "Studnet" (typo) — corrected to "Student"
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id:" + id + " not found"));
        studentRepo.delete(student);
    }
}