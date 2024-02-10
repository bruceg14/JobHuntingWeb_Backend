package com.example.back_end;
import org.springframework.data.jpa.domain.Specification;
public class JobApplicationSpecification {
    public static Specification<JobApplication> companyNameContains(String company) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("company")), "%" + company.toLowerCase() + "%");
    }
}
