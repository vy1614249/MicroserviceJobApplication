package com.vikash.application_ms.controller;

import com.vikash.application_ms.dto.ApplicationDto;
import com.vikash.application_ms.model.ApplicationStatus;
import com.vikash.application_ms.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // User applies to a job
    @PostMapping
    public ResponseEntity<ApplicationDto> apply(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto saved = applicationService.apply(applicationDto);
        return ResponseEntity.ok(saved);
    }

    // List applications by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(applicationService.getApplicationsByUser(userId));
    }

    // List applicants by job
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }

    // Update status of an application
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationDto> updateStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateStatus(applicationId, status));
    }
}
