package com.example.QuoraAPI.dto;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    
    private UUID id;
    
    private String username;

    private String email;

    private String bio;

    private Set<UUID> questions;
    
    private Set<UUID> answers;

    private Set<UUID> comments;
    
    private Set<UUID> followers;

    private Set<UUID> follows;
}
