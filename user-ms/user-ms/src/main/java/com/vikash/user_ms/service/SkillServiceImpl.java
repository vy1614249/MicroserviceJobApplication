package com.vikash.user_ms.service;

import com.vikash.user_ms.dto.SkillDto;
import com.vikash.user_ms.model.Skill;
import com.vikash.user_ms.model.User;
import com.vikash.user_ms.repository.SkillRepository;
import com.vikash.user_ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public SkillDto addSkill(Long userId, SkillDto skillDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = modelMapper.map(skillDto, Skill.class);
        skill.setUser(user);

        Skill saved = skillRepository.save(skill);
        return modelMapper.map(saved, SkillDto.class);
    }

    @Override
    public List<SkillDto> getSkillsByUserId(Long userId) {
        List<Skill> skills = skillRepository.findByUserId(userId);
        return skills.stream()
                .map(skill -> modelMapper.map(skill, SkillDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSkill(Long userId, Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        if (!skill.getUser().getId().equals(userId)) {
            throw new RuntimeException("Skill does not belong to this user");
        }
        skillRepository.delete(skill);
    }
}
