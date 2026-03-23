package com.jobtracker.service;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.Status;
import com.jobtracker.entity.User;
import com.jobtracker.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobApplication addJob(JobApplication job, User user) {

    int count = jobRepository.countByUser(user);

    job.setUser(user);
    job.setJobNumber(count + 1); // ✅ per-user numbering
    job.setStatus(Status.APPLIED);

    return jobRepository.save(job);
}

    public Page<JobApplication> searchJobs(User user, String keyword, Pageable pageable) {
    return jobRepository.findByUserAndCompanyContainingIgnoreCase(user, keyword, pageable);
}

    public Page<JobApplication> getJobs(User user, Pageable pageable) {
    return jobRepository.findByUser(user, pageable);
}

    public JobApplication updateStatus(Long id, String status, User user) {
    JobApplication job = jobRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Job not found"));

    if (!job.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Unauthorized");
    }

    job.setStatus(Status.valueOf(status));
    return jobRepository.save(job);
}

public JobApplication updateJob(Long id, JobApplication updatedJob, User user) {
    JobApplication job = jobRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Job not found"));

    if (!job.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Unauthorized");
    }

    job.setCompany(updatedJob.getCompany());
    job.setRole(updatedJob.getRole());

    return jobRepository.save(job);
}

public void deleteJob(Long id, User user) {
    JobApplication job = jobRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Job not found"));

    if (!job.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Unauthorized");
    }

    jobRepository.delete(job);
}
}