package com.example.infrastructure.outputAdapters;

import com.example.domain.entities.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<UserDomain, Long> {
    UserDomain findUserDomainByName(String name);
}
