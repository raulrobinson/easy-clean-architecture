package com.example.application;

import com.example.domain.entities.UserDomain;
import com.example.infrastructure.inputAdapters.dto.RequestDto;
import com.example.infrastructure.outputPorts.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class IUserUseCaseTestU {
    @Mock
    UserRepository repository;
    @InjectMocks
    IUserUseCase iUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        when(repository.getAllUsers()).thenReturn(List.of(new UserDomain()));

        List<UserDomain> result = iUserUseCase.getAllUsers();
        Assertions.assertEquals(List.of(new UserDomain()), result);
    }

    @Test
    void testGetUserById() {
        when(repository.findUserById(anyLong())).thenReturn(new UserDomain());

        UserDomain result = iUserUseCase.getUserById(Long.valueOf(1));
        Assertions.assertEquals(new UserDomain(), result);
    }

    @Test
    void testDeleteUser() {
        when(repository.findUserById(anyLong())).thenReturn(new UserDomain());

        UserDomain result = iUserUseCase.deleteUser(Long.valueOf(1));
        verify(repository).deleteUser(anyLong());
        Assertions.assertEquals(new UserDomain(), result);
    }

    @Test
    void testCreateUser() {
        when(repository.findUserDomainByName(anyString())).thenReturn(new UserDomain());
        when(repository.save(any(UserDomain.class))).thenReturn(new UserDomain());

        UserDomain result = iUserUseCase.createUser(new RequestDto("name", "country"));
        Assertions.assertEquals(new UserDomain(), result);
    }

    @Test
    void testUpdateUser() {
        when(repository.findUserById(anyLong())).thenReturn(new UserDomain());
        when(repository.save(any(UserDomain.class))).thenReturn(new UserDomain());

        UserDomain result = iUserUseCase.updateUser(Long.valueOf(1), new RequestDto("name", "country"));
        Assertions.assertEquals(new UserDomain(), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme