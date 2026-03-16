package com.example.LearningManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String specialization;

    @Min(value = 0, message = "Experience years cannot be negative")
    @Max(value = 60, message = "Experience years seems unrealistic")
    private short experienceYears;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> courses;

    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public short getExperienceYears() { return experienceYears; }
    public void setExperienceYears(short experienceYears) { this.experienceYears = experienceYears; }

    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}