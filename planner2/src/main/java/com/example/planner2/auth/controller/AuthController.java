package com.example.planner2.auth.controller;

import com.example.planner2.auth.dto.LoginRequest;
import com.example.planner2.auth.dto.LoginResponse;
import com.example.planner2.auth.dto.SessionUser;
import com.example.planner2.auth.service.AuthService;
import com.example.planner2.user.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {

        // 로그인 검증
        User user = authService.login(request);

        // 세션용 DTO 생성
        SessionUser sessionUser =
                new SessionUser(user.getId(), user.getEmail(), user.getUsername());

        // 세션에 저장
        session.setAttribute("loginUser", sessionUser);

        // 메시지 응답
        LoginResponse response =
                new LoginResponse("로그인 성공");

        return ResponseEntity.ok(new LoginResponse("로그인 성공"));
    }
}

