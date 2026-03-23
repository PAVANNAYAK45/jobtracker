package com.jobtracker.dto;

public class JobResponse {

    private Long id;
    private String company;
    private String role;
    private String status;
    private int jobNumber;

      public JobResponse() {}

    public JobResponse(Long id, String company, String role, String status, int jobNumber) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.status = status;
        this.jobNumber = jobNumber;
    }

    public Long getId() { return id; }
    public String getCompany() { return company; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
    public int getJobNumber() { return jobNumber; }
}