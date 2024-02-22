package com.example.back_end;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer>, JpaSpecificationExecutor<JobApplication> {
    Iterable<JobApplication> findByDate(String date);
    Iterable<JobApplication> findByCompany(String company);

    @Query("SELECT j.date, COUNT(j) AS applicationCount FROM JobApplication j GROUP BY j.date ORDER BY applicationCount DESC")
    List<List> findDateWithMostApplications();
}

