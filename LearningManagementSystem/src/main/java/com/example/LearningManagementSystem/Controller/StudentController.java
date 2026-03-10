package com.example.LearningManagementSystem.Controller;


import com.example.LearningManagementSystem.DTO.StudentDTO;
import com.example.LearningManagementSystem.DTO.StudentProfileDTO;
import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService service;
    public StudentController(StudentService service){
       this.service=service;
    }

    @GetMapping
    public List<StudentProfileDTO> getAllStudents(){
        return service.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<StudentProfileDTO> addStudent(@RequestBody Student student){
       return ResponseEntity.status(HttpStatus.OK)
               .body(service.addStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id){
        service.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("deleted student with id:"+id);
    }


}
