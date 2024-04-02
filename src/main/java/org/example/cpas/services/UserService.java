package org.example.cpas.services;

import org.example.cpas.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
}
