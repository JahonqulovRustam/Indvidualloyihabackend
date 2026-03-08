package com.medicore.hms.security;

import com.medicore.hms.model.User;
import com.medicore.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedUsers() {
        return args -> {
            // ensure admin exists with known password
            repository.findByUsername("admin").ifPresentOrElse(user -> {
                user.setPassword(passwordEncoder.encode("Admin@123"));
                repository.save(user);
            }, () -> {
                repository.save(User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("Admin@123"))
                        .fullName("Administrator")
                        .role(User.Role.ADMIN)
                        .build());
            });

            if (repository.findByUsername("doctor1").isEmpty()) {
                repository.save(User.builder()
                        .username("doctor1")
                        .password(passwordEncoder.encode("1234"))
                        .fullName("Dr. Ahmedov")
                        .role(User.Role.DOCTOR)
                        .build());
            }
            if (repository.findByUsername("nurse1").isEmpty()) {
                repository.save(User.builder()
                        .username("nurse1")
                        .password(passwordEncoder.encode("1234"))
                        .fullName("Guli Qodirova")
                        .role(User.Role.NURSE)
                        .build());
            }
        };
    }
}
