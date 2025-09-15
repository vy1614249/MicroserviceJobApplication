package com.vikash.jobms.job.dto;

import com.vikash.jobms.job.external.Company;
import com.vikash.jobms.job.model.Job;
import lombok.Data;

@Data
public class JobWithCompanyDTO {
    private Job job;
    private Company company;
}
