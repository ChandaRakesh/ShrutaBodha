package com.shrutabodha.backend.dto;

import lombok.Builder;
import lombok.Getter;


import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class UserResponse {
    private UUID id;
    private String email;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
}
