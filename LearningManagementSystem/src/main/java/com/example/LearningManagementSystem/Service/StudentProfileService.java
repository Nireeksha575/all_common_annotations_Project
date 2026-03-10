package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Entity.StudentProfile;
import com.example.LearningManagementSystem.Exception.ProfileAlreadySetException;
import com.example.LearningManagementSystem.Exception.ProfileNotFoundException;
import com.example.LearningManagementSystem.Exception.StudentNotFoundException;
import com.example.LearningManagementSystem.Repository.StudentProfileRepo;
import com.example.LearningManagementSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService {
    private StudentProfileRepo studentProfileRepo;
    public StudentProfileService(StudentProfileRepo studentProfileRepo){
        this.studentProfileRepo=studentProfileRepo;
    }
    @Autowired
    private StudentRepo studentRepo;

    public List<StudentProfile> getAllProfile(){
        return studentProfileRepo.findAll();
    }

    public StudentProfile addProfile(StudentProfile studentProfile, long id) {
        Student student= studentRepo.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student with id:"+id+" is not found"));

        if(student.getProfile()!=null){
            throw new ProfileAlreadySetException("Student with id:"+id+" has a profile");
        }
            student.setProfile(studentProfile);
            studentProfile.setStudent(student);
            Student updatedStudent=studentRepo.save(student);
            return updatedStudent.getProfile();
    }

    public void deleteProfile(long id) {
        StudentProfile profile=studentProfileRepo.findById(id)
                .orElseThrow(()->new ProfileNotFoundException("Profile with id:"+id+" not found"));
        Student student=profile.getStudent();
        if(student!=null){
            student.setProfile(null);
        }
        studentProfileRepo.delete(profile);
    }
}
