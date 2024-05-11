package com.example.infrastructure.outputPorts;

import com.example.domain.entities.UserDomain;
import com.example.infrastructure.outputAdapters.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    private final EntityRepository repository;

    @Autowired
    public UserRepository(EntityRepository repository) {
        this.repository = repository;
    }

    public List<UserDomain> getAllUsers() {
        return repository.findAll();
    }

    public UserDomain findUserById(Long userId) {
        return repository.findById(userId).orElse(null);
    }

    public void deleteUser(Long userId) {
        repository.findById(userId).ifPresent(repository::delete);
    }

    public UserDomain findUserDomainByName(String name) {
        return repository.findUserDomainByName(name);
    }

    public UserDomain save(UserDomain userDomain) {
        return repository.save(userDomain);
    }
}
