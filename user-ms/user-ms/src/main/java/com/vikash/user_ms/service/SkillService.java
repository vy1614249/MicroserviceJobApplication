package com.vikash.user_ms.service;

import com.vikash.user_ms.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto addSkill(Long userId, SkillDto skillDto);
    List<SkillDto> getSkillsByUserId(Long userId);
    void deleteSkill(Long userId, Long skillId);
}

