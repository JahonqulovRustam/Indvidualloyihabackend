package com.medicore.hms.service;

import com.medicore.hms.dto.ChangePasswordRequest;
import com.medicore.hms.dto.UserDto;
import com.medicore.hms.exception.BadRequestException;
import com.medicore.hms.exception.ConflictException;
import com.medicore.hms.exception.ResourceNotFoundException;
import com.medicore.hms.model.User;
import com.medicore.hms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDto createUser(User user) {
        if (user.getUsername() != null && userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ConflictException("Username already exists: " + user.getUsername());
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode("medicore123"));
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername(user.getEmail() != null ? user.getEmail() : "user_" + System.currentTimeMillis());
        }

        return UserDto.fromEntity(userRepository.save(user));
    }

    public UserDto getUserDtoById(Long id) {
        return UserDto.fromEntity(getUserEntityById(id));
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserDto updateUser(Long id, User userDetails) {
        User user = getUserEntityById(id);

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

        if (userDetails.getUsername() != null && !userDetails.getUsername().isEmpty()
                && !userDetails.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(userDetails.getUsername()).isPresent()) {
                throw new ConflictException("Username already exists: " + userDetails.getUsername());
            }
            user.setUsername(userDetails.getUsername());
        }

        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return UserDto.fromEntity(userRepository.save(user));
    }

    public void changePassword(String username, ChangePasswordRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
