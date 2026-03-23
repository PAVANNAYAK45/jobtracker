package com.jobtracker.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobApplication, Long> {
    Page<JobApplication> findByUserAndCompanyContainingIgnoreCase(User user, String company, Pageable pageable);

    Page<JobApplication> findByUser(User user, Pageable pageable);
    int countByUser(User user);
}