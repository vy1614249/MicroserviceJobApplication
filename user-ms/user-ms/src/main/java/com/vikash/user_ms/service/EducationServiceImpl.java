package com.vikash.user_ms.service;

import com.vikash.user_ms.dto.EducationDto;
import com.vikash.user_ms.model.Education;
import com.vikash.user_ms.model.User;
import com.vikash.user_ms.repository.EducationRepository;
import com.vikash.user_ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public EducationDto addEducation(Long userId, EducationDto educationDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Education education = modelMapper.map(educationDto, Education.class);
        education.setUser(user);

        Education saved = educationRepository.save(education);
        return modelMapper.map(saved, EducationDto.class);
    }

    @Override
    public List<EducationDto> getEducationByUserId(Long userId) {
        List<Education> list = educationRepository.findByUserId(userId);
        return list.stream()
                .map(edu -> modelMapper.map(edu, EducationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEducation(Long userId, Long educationId) {
        Education education = educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education not found"));
        if (!education.getUser().getId().equals(userId)) {
            throw new RuntimeException("Education does not belong to this user");
        }
        educationRepository.delete(education);
    }
}

