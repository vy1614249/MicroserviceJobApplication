package com.vikash.user_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {

    private Long id;
    private String institution;
    private String degree;
    private String field;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
