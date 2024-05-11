package com.example.application;

import com.example.domain.entities.UserDomain;
import com.example.infrastructure.outputAdapters.EntityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IUserUseCaseTest {

    @Mock
    EntityRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        List<UserDomain> users = repository.findAll();
        Assertions.assertNotNull(users);
    }

    @Test
    void getUserById() {
        Optional<UserDomain> user = repository.findById(1L);
        Assertions.assertNotNull(user);
    }

    @Test
    void createUser() {
        UserDomain user = repository.findUserDomainByName("name");
        UserDomain userSave = new UserDomain();

        userSave.setId(1L);
        userSave.setName("Raul Bolivar");
        userSave.setCountry("Colombia");

        if (user != null) {
            UserDomain userDomain = repository.save(userSave);
            Assertions.assertNotNull(userDomain);
        }
        Assertions.assertNull(user);
    }

    @Test
    void deleteUser() {
        Optional<UserDomain> user = repository.findById(1L);
        user.ifPresent(userDomain -> repository.delete(userDomain));

        Optional<UserDomain> userDeleted = repository.findById(1L);
        assertFalse(userDeleted.isPresent());
    }
}