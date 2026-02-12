package com.example.planner2.user.controller;

import com.example.planner2.user.dto.CreateUserRequest;
import com.example.planner2.user.dto.CreateUserResponse;
import com.example.planner2.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {

    private final UserService userService;
    // 비즈니스 로직을 서비스에게 맡기기 위해서, 다른 객체로 변경하지 않게 하기 위해서

    // 회원가입
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse result = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 유저 선택 조회

    // 유저 전체 조회

    // 유저 삭제
}
