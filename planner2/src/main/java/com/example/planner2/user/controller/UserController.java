package com.example.planner2.user.controller;

import com.example.planner2.auth.dto.SessionUser;
import com.example.planner2.user.dto.*;
import com.example.planner2.user.entity.User;
import com.example.planner2.user.repository.UserRepository;
import com.example.planner2.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{userId}")
    public ResponseEntity<GetOneUserResponse> getMyInfo(@SessionAttribute("user") SessionUser sessionUser) {
        String email = sessionUser.getEmail(); // 세션에서 로그인한 유저 이메일 가져오기
        GetOneUserResponse response = userService.getOneUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<List<GetOneUserResponse>> getAllUsers(HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute("user");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<GetOneUserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    // 유저 수정
    @PutMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @RequestBody UpdateUserRequest request,
            HttpSession session
    ) {
        // 세션에서 로그인 유저 정보 가져오기
        SessionUser sessionUser = (SessionUser) session.getAttribute("loginUser");
        if (sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Long loginUserId = sessionUser.getId();

        // Service 호출
        UpdateUserResponse response = userService.update(loginUserId, request);

        return ResponseEntity.ok(response);
    }


    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id,
            HttpSession session
    ) {
        // 1. 세션에서 로그인 유저 확인
        SessionUser sessionUser = (SessionUser) session.getAttribute("loginUser");
        if (sessionUser == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        // 2. Service 호출
        userService.delete(id, sessionUser.getId());

        // 3. 삭제 완료 메시지 반환
        return ResponseEntity.ok("유저 삭제가 완료 됐습니다.");
    }
}