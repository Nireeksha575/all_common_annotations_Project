package com.example.LearningManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.util.List;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String specialization;
    private short experienceYears;
    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> courses;

    public @NonNull String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public String getSpecialization() {
        return specialization;
    }

    public short getExperienceYears() {
        return experienceYears;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setExperienceYears(short experienceYears) {
        this.experienceYears = experienceYears;
    }


}
