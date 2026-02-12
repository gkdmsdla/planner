package com.example.planner2.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.SplittableRandom;

@Getter
public class CreateUserRequest {

    @NotBlank // 공백이 아닌 문자가 1개 이상 있어야 됨 (null 거부)
    private String username;
    @Email // 이메일 검증
    private String email;
    @Size(min = 8, max = 15) // 사이즈
    private String password;
}
