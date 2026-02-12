package com.example.planner2.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateUserRequest {

    @NotBlank
    private String username;
}