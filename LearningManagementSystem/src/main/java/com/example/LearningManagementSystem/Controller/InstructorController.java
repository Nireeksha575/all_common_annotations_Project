package com.example.LearningManagementSystem.Controller;

import com.example.LearningManagementSystem.Entity.Instructor;
import com.example.LearningManagementSystem.Service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.createInstructor(instructor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructor) {
        return ResponseEntity.ok(instructorService.updateInstructor(id, instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }
}