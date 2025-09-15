package com.vikash.jobms.job.service;

import com.vikash.jobms.job.dto.JobRequestDTO;
import com.vikash.jobms.job.dto.JobWithCompanyDTO;
import com.vikash.jobms.job.model.Job;
import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> allJobs();
    List<JobWithCompanyDTO> jobByCompany(Long companyId);
    JobWithCompanyDTO findById(Long id);
    String createJob(JobRequestDTO job);
    String deleteJob(Long id);
    String updateJob(Long id,JobRequestDTO job);
    List<Job> findJobByCompanyName(String name);
}
