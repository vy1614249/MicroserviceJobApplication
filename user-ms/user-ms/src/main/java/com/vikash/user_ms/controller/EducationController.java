package com.vikash.user_ms.controller;

import com.vikash.user_ms.dto.EducationDto;
import com.vikash.user_ms.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    // Add an education record for a user
    @PostMapping
    public ResponseEntity<EducationDto> addEducation(
            @PathVariable Long userId,
            @RequestBody EducationDto educationDto) {
        EducationDto saved = educationService.addEducation(userId, educationDto);
        return ResponseEntity.ok(saved);
    }

    // Get all education records of a user
    @GetMapping
    public ResponseEntity<List<EducationDto>> getEducationList(@PathVariable Long userId) {
        return ResponseEntity.ok(educationService.getEducationByUserId(userId));
    }

    // Delete an education record of a user
    @DeleteMapping("/{educationId}")
    public ResponseEntity<Void> deleteEducation(
            @PathVariable Long userId,
            @PathVariable Long educationId) {
        educationService.deleteEducation(userId, educationId);
        return ResponseEntity.noContent().build();
    }
}
