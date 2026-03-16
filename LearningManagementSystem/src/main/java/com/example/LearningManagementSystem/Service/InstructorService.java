package com.example.LearningManagementSystem.Service;

import com.example.LearningManagementSystem.Entity.Instructor;
import com.example.LearningManagementSystem.Exception.DuplicateResourceException;
import com.example.LearningManagementSystem.Exception.ResourceNotFoundException;
import com.example.LearningManagementSystem.Repository.InstructorRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class InstructorService {

    private final InstructorRepo instructorRepository;

    public InstructorService(InstructorRepo instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", id));
    }

    @Transactional
    public Instructor createInstructor(Instructor instructor) {
        if (instructorRepository.existsByEmail(instructor.getEmail())) {
            throw new DuplicateResourceException("Instructor with email '" + instructor.getEmail() + "' already exists.");
        }
        return instructorRepository.save(instructor);
    }

    @Transactional
    public Instructor updateInstructor(Long id, Instructor updated) {
        Instructor existing = getInstructorById(id);
        if (!existing.getEmail().equals(updated.getEmail()) &&
                instructorRepository.existsByEmail(updated.getEmail())) {
            throw new DuplicateResourceException("Email '" + updated.getEmail() + "' is already in use.");
        }
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPassword(updated.getPassword());
        existing.setSpecialization(updated.getSpecialization());
        existing.setExperienceYears(updated.getExperienceYears());
        return instructorRepository.save(existing);
    }

    @Transactional
    public void deleteInstructor(Long id) {
        getInstructorById(id);
        instructorRepository.deleteById(id);
    }
}