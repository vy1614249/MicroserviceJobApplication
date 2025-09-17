package com.vikash.user_ms.service;

import com.vikash.user_ms.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);

    boolean existsById(Long id);
}
