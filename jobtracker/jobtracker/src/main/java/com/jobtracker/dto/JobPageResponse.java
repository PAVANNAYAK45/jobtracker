package com.jobtracker.dto;

import java.util.List;

public class JobPageResponse {

    private List<JobResponse> jobs;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;

    public JobPageResponse() {}

    public JobPageResponse(List<JobResponse> jobs, int page, int size, int totalPages, long totalElements) {
        this.jobs = jobs;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<JobResponse> getJobs() { return jobs; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public int getTotalPages() { return totalPages; }
    public long getTotalElements() { return totalElements; }
}