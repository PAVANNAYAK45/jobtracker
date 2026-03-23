package com.jobtracker.controller;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.User;
import com.jobtracker.repository.UserRepository;
import com.jobtracker.security.JwtUtil;
import com.jobtracker.service.JobService;

import com.jobtracker.dto.ApiResponse;

import com.jobtracker.dto.JobPageResponse;
import com.jobtracker.dto.JobResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private User getUser(String token) {
        if (token == null || token.isBlank())
            return null;
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        return userRepository.findByEmail(email).orElse(null);
    }

    @PostMapping
    public JobResponse addJob(@RequestHeader("Authorization") String token,
            @RequestBody JobApplication job) {

        User user = getUser(token);
        JobApplication savedJob = jobService.addJob(job, user);

        return new JobResponse(
                savedJob.getId(),
                savedJob.getCompany(),
                savedJob.getRole(),
                savedJob.getStatus().name(),
                savedJob.getJobNumber()
            );
    }

    @GetMapping
    public ApiResponse<JobPageResponse> getJobs(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        User user = getUser(token);

        Pageable pageable = PageRequest.of(page, size);
        Page<JobApplication> jobPage = jobService.getJobs(user, pageable);

        List<JobResponse> jobs = jobPage.getContent().stream()
                .map(job -> new JobResponse(
                        job.getId(),
                        job.getCompany(),
                        job.getRole(),
                        job.getStatus().name(),
                        job.getJobNumber()))
                .toList();

        return new ApiResponse<JobPageResponse>(
                true,
                "Jobs fetched successfully",
                new JobPageResponse(
                        jobs,
                        jobPage.getNumber(),
                        jobPage.getSize(),
                        jobPage.getTotalPages(),
                        jobPage.getTotalElements()));
    }

    @PutMapping("/{id}/status")
    public JobResponse updateStatus(@PathVariable Long id,
            @RequestParam String status,
            @RequestHeader("Authorization") String token) {

        User user = getUser(token);
        JobApplication job = jobService.updateStatus(id, status, user);

        return new JobResponse(
                job.getId(),
                job.getCompany(),
                job.getRole(),
                job.getStatus().name(),
                job.getJobNumber()
            );
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        User user = getUser(token);
        jobService.deleteJob(id, user);

        return "Job deleted successfully";
    }

    @PutMapping("/{id}")
    public JobResponse updateJob(@RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody JobApplication job) {

        User user = getUser(token);
        JobApplication updated = jobService.updateJob(id, job, user);

        return new JobResponse(
                updated.getId(),
                updated.getCompany(),
                updated.getRole(),
                updated.getStatus().name(),
                updated.getJobNumber());
    }

    @GetMapping("/search")
public ApiResponse<?> searchJobs(
        @RequestHeader("Authorization") String token,
        @RequestParam String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "2") int size) {

    User user = getUser(token);
    Pageable pageable = PageRequest.of(page, size);

    Page<JobApplication> jobPage = jobService.searchJobs(user, keyword, pageable);

    return new ApiResponse<>(true, "Search results", jobPage);
}
}