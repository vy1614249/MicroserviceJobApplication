package com.vikash.user_ms.service;

import com.vikash.user_ms.dto.UserDto;
import com.vikash.user_ms.model.User;
import com.vikash.user_ms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        user.getSkills().forEach(skill -> skill.setId(null));
        user.getEducationList().forEach(edu -> edu.setId(null));
        user.getSkills().forEach(skill -> skill.setUser(user));
        user.getEducationList().forEach(edu -> edu.setUser(user));

        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Update simple fields
        existing.setFullName(userDto.getFullName());
        existing.setEmail(userDto.getEmail());
        existing.setPhone(userDto.getPhone());
        existing.setLocation(userDto.getLocation());
        existing.setHeadline(userDto.getHeadline());
        existing.setAbout(userDto.getAbout());
        //existing.setResumeUrl(userDto.getResumeUrl());
        User updated = userRepository.save(existing);
        return modelMapper.map(updated, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
