package com.vikash.user_ms.service;

import com.vikash.user_ms.dto.EducationDto;

import java.util.List;

public interface EducationService {
    EducationDto addEducation(Long userId, EducationDto educationDto);
    List<EducationDto> getEducationByUserId(Long userId);
    void deleteEducation(Long userId, Long educationId);
}
