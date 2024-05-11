package com.example.infrastructure.inputPorts;

import com.example.domain.entities.UserDomain;
import com.example.infrastructure.inputAdapters.dto.RequestDto;

import java.util.List;

public interface UserInputPort {
    List<UserDomain> getAllUsers();
    UserDomain getUserById(Long userId);
    UserDomain deleteUser(Long userId);
    UserDomain createUser(RequestDto request);
    UserDomain updateUser(Long userId, RequestDto requestDto);
}
