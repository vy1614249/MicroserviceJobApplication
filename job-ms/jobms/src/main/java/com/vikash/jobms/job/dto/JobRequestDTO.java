package com.vikash.jobms.job.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class JobRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 100)
    private String description;

    private String minSalary;

    private String maxSalary;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}

