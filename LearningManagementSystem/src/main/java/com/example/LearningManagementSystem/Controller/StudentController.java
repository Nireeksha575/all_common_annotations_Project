package com.example.LearningManagementSystem.Controller;


import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
public class StudentController {
    private StudentService service;
    public StudentController(StudentService service){
       this.service=service;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return service.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
       return ResponseEntity.status(HttpStatus.OK)
               .body(service.addStudent(student));
    }
}
