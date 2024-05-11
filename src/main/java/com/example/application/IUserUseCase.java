package com.example.application;

import com.example.domain.entities.UserDomain;
import com.example.infrastructure.inputAdapters.dto.RequestDto;
import com.example.infrastructure.inputPorts.IUser;
import com.example.infrastructure.outputPorts.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.example.common.utils.Shield.blindStr;
import static com.example.infrastructure.mappers.UserMapper.ToDomainFromDto;

@Component
public class IUserUseCase implements IUser {

    private final UserRepository repository;

    @Autowired
    public IUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserDomain> getAllUsers() {
        List<UserDomain> users = repository.getAllUsers();
        if (!users.isEmpty()) {
            return users;
        }
        return null;
    }

    @Override
    public UserDomain getUserById(Long userId) {
        return repository.findUserById(userId);
    }

    @Override
    public UserDomain deleteUser(Long userId) {
        UserDomain user = repository.findUserById(userId);
        if (user != null) {
            repository.deleteUser(userId);
        }
        return user;
    }

    @Override
    public UserDomain createUser(RequestDto request) {
        var userFind = repository.findUserDomainByName(request.getName());
        if (userFind != null) {
            return null;
        }
        return repository.save(ToDomainFromDto(request));
    }

    @Override
    public UserDomain updateUser(Long userId, RequestDto requestDto) {
        UserDomain user = repository.findUserById(userId);
        if (user != null && Objects.equals(Objects.requireNonNull(user).getId(), userId)) {
            UserDomain userDomain = new UserDomain();
            userDomain.setId(userId);
            userDomain.setName(blindStr(requestDto.getName()));
            userDomain.setCountry(blindStr(requestDto.getCountry()));
            return repository.save(userDomain);
        }
        return null;
    }
}
