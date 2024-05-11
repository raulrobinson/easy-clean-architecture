package com.example.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProfileCheckConfig {

    @Value("${spring.profiles.active}")
    private String activeProfiles;

    @Bean
    public ApplicationRunner profileCheck() {
        return args -> log.info("Active profiles: {}", activeProfiles);
    }
}
