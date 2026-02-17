package com.saladin.dto;

import com.saladin.entity.Role;
import com.saladin.entity.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Status status;
    private LocalDateTime createdAt;
}
