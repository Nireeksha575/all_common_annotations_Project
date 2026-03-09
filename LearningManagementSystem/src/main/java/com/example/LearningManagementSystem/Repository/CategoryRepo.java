package com.example.LearningManagementSystem.Repository;

import com.example.LearningManagementSystem.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Long, Category> {
}
