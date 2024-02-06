package com.example.back_end;


import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer> {
    Iterable<JobApplication> findByDate(String date);
}
