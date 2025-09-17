package com.vikash.user_ms.controller;

import com.vikash.user_ms.dto.SkillDto;
import com.vikash.user_ms.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<SkillDto> addSkill(
            @PathVariable Long userId,
            @RequestBody SkillDto skillDto) {
        SkillDto saved = skillService.addSkill(userId, skillDto);
        return ResponseEntity.ok(saved);
    }


    @GetMapping
    public ResponseEntity<List<SkillDto>> getSkills(@PathVariable Long userId) {
        return ResponseEntity.ok(skillService.getSkillsByUserId(userId));
    }


    @DeleteMapping("/{skillId}")
    public ResponseEntity<Void> deleteSkill(
            @PathVariable Long userId,
            @PathVariable Long skillId) {
        skillService.deleteSkill(userId, skillId);
        return ResponseEntity.noContent().build();
    }
}