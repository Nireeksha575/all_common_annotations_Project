package com.example.LearningManagementSystem.Controller;

import com.example.LearningManagementSystem.Entity.StudentProfile;
import com.example.LearningManagementSystem.Service.StudentProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentprofiles")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @GetMapping
    public List<StudentProfile> getAllprofile() {
        return service.getAllProfile();
    }

    @PostMapping
    public ResponseEntity<StudentProfile> addProfile(
            @Valid @RequestBody StudentProfile studentProfile,
            @RequestParam long id) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addProfile(studentProfile, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable long id) {
        service.deleteProfile(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Profile with id:" + id + " deleted");
    }
}