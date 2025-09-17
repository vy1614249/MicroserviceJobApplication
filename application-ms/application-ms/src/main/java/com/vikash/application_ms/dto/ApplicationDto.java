package com.vikash.application_ms.dto;

import com.vikash.application_ms.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private Long id;
    private Long userId;
    private Long jobId;
    //private String resumeUrl;
    private ApplicationStatus status;
    private LocalDateTime appliedAt = LocalDateTime.now();
}

