package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Category;
import com.example.LearningManagementSystem.Exception.DuplicateResourceException;
import com.example.LearningManagementSystem.Exception.ResourceNotFoundException;
import com.example.LearningManagementSystem.Repository.CategoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  // Default all methods to read-only transactions
public class CategoryService {

    private final CategoryRepo categoryRepository;

    public CategoryService(CategoryRepo categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
    }

    @Transactional  // Overrides class-level readOnly=true for write operations
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new DuplicateResourceException("Category with name '" + category.getName() + "' already exists.");
        }
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Long id, Category updated) {
        Category existing = getCategoryById(id);
        if (!existing.getName().equals(updated.getName()) &&
                categoryRepository.existsByName(updated.getName())) {
            throw new DuplicateResourceException("Category with name '" + updated.getName() + "' already exists.");
        }
        existing.setName(updated.getName());
        return categoryRepository.save(existing);
    }

    @Transactional
    public void deleteCategory(Long id) {
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }
}