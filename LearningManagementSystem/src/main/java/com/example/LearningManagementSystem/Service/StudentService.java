package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Entity.StudentProfile;
import com.example.LearningManagementSystem.Repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepo studentRepo;
    public StudentService(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student addStudent(Student student){
        StudentProfile studentProfile=student.getProfile();
        if(studentProfile!=null){
            studentProfile.setStudent(student);
        }
        return studentRepo.save(student);
    }
}
