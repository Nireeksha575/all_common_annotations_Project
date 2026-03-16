package com.example.LearningManagementSystem.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @NotBlank(message = "Course name is required")
    @Size(min = 3, max = 150, message = "Course name must be between 3 and 150 characters")
    private String courseName;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}