package com.example.LearningManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Size(max = 300, message = "Address cannot exceed 300 characters")
    private String address;

    @Column(name = "date_of_birth")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Size(max = 200, message = "Education field cannot exceed 200 characters")
    private String education;

    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}