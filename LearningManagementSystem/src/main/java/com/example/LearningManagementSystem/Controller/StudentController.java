package com.example.LearningManagementSystem.Controller;

import com.example.LearningManagementSystem.DTO.StudentProfileDTO;
import com.example.LearningManagementSystem.Entity.Student;
import com.example.LearningManagementSystem.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentProfileDTO> getAllStudents() {
        return service.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<StudentProfileDTO> addStudent(@Valid @RequestBody Student student) {
        // BUG FIX: was HttpStatus.OK (200), should be CREATED (201) for a POST
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        service.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("deleted student with id:" + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable long id, @Valid @RequestBody Student student) {
        service.updateStudent(id, student);
        return ResponseEntity.status(HttpStatus.OK)
                .body("updated student with id:" + id);
    }
}