package com.vikash.application_ms.service;

import com.vikash.application_ms.client.JobClient;
import com.vikash.application_ms.client.UserClient;
import com.vikash.application_ms.dto.ApplicationDto;
import com.vikash.application_ms.model.Application;
import com.vikash.application_ms.model.ApplicationStatus;
import com.vikash.application_ms.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    private final JobClient jobClient;
    private final UserClient userClient;

    @Override
    public ApplicationDto apply(ApplicationDto applicationDto) {

        Application application = modelMapper.map(applicationDto, Application.class);
        boolean jobValid = jobClient.isJobWithIdValid(application.getJobId());
        if (!jobValid) { // if not valid, throw
            throw new RuntimeException("Job id " + application.getJobId() + " is invalid!");
        }

        boolean userValid = userClient.isUserWithIdValid(application.getUserId());
        if (!userValid) {
            throw new RuntimeException("User id " + application.getUserId() + " is invalid!");
        }

        application.setId(null); // ensure new
        application.setStatus(ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());

        Application saved = applicationRepository.save(application);
        return modelMapper.map(saved, ApplicationDto.class);
    }

    @Override
    public List<ApplicationDto> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId).stream()
                .map(app -> modelMapper.map(app, ApplicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId).stream()
                .map(app -> modelMapper.map(app, ApplicationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDto updateStatus(Long applicationId, ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        Application updated = applicationRepository.save(application);
        return modelMapper.map(updated, ApplicationDto.class);
    }
}
