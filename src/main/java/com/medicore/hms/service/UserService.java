package com.medicore.hms.service;

import com.medicore.hms.model.User;
import com.medicore.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        // Provide a default password if not set
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode("medicore123"));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Ensure username is set to email or phone if missing
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername(user.getEmail() != null ? user.getEmail() : "user_" + System.currentTimeMillis());
        }

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);

        if (userDetails.getFullName() != null)
            user.setFullName(userDetails.getFullName());
        if (userDetails.getRole() != null)
            user.setRole(userDetails.getRole());
        if (userDetails.getSpecialty() != null)
            user.setSpecialty(userDetails.getSpecialty());
        if (userDetails.getPhone() != null)
            user.setPhone(userDetails.getPhone());
        if (userDetails.getEmail() != null)
            user.setEmail(userDetails.getEmail());
        if (userDetails.getStatus() != null)
            user.setStatus(userDetails.getStatus());

        // Update username if provided and unique
        if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()
                && !userDetails.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(userDetails.getUsername()).isPresent()) {
                throw new RuntimeException("Username already exists: " + userDetails.getUsername());
            }
            user.setUsername(userDetails.getUsername());
        }

        // Update password only if provided
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
