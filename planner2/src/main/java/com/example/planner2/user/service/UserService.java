package com.example.planner2.user.service;

import com.example.planner2.user.dto.CreateUserRequest;
import com.example.planner2.user.dto.CreateUserResponse;
import com.example.planner2.user.entity.User;
import com.example.planner2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        if (userRepository.existsByUsername(request.getUsername())) {

            throw new IllegalArgumentException("이미 사용 중인 유저 이름입니다.");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);
        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreatedAt(),
                savedUser.getModifiedAt()
        );
    }

    // 유저 선택 조회


    // 유저 전체 조회


    // 유저 삭제

}
