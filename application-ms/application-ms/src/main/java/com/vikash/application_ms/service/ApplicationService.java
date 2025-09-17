package com.vikash.application_ms.service;

import com.vikash.application_ms.dto.ApplicationDto;
import com.vikash.application_ms.model.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    ApplicationDto apply(ApplicationDto applicationDto);
    List<ApplicationDto> getApplicationsByUser(Long userId);
    List<ApplicationDto> getApplicationsByJob(Long jobId);
    ApplicationDto updateStatus(Long applicationId, ApplicationStatus status);
}

