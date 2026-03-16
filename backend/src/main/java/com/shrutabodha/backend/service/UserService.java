package com.shrutabodha.backend.service;

import com.shrutabodha.backend.dto.CreateUserRequest;
import com.shrutabodha.backend.dto.UserResponse;
import com.shrutabodha.backend.entity.User;
import com.shrutabodha.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
        User user= User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .name(request.getName())
                .isActive(true)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        User savedUser=userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();

    }

    public UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
    public List<UserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream().map(this::mapToResponse)
                .toList();
    }
    public UserResponse getUserById(UUID id){
        User user=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));

        return  mapToResponse(user);
    }
}
