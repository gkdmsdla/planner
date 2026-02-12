package com.example.planner2.user.service;

import com.example.planner2.auth.dto.SessionUser;
import com.example.planner2.user.dto.*;
import com.example.planner2.user.entity.User;
import com.example.planner2.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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
    public GetOneUserResponse getOneUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));

        return new GetOneUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }

    // 유저 전체 조회
    public List<GetOneUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<GetOneUserResponse> dtos = new ArrayList<>();
        for (User user : users) {
            GetOneUserResponse dto = new GetOneUserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getCreatedAt(),
                    user.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 유저 수정
    @Transactional
    public UpdateUserResponse update(Long userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));

        // username만 수정
        user.update(request.getUsername());

        User updatedUser = userRepository.save(user);

        return new UpdateUserResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getCreatedAt(),
                updatedUser.getModifiedAt()
        );
    }


    // 유저 삭제
    @Transactional
    public void delete(Long targetUserId, Long loginUserId) {
        // 로그인 유저와 삭제 대상이 같은지 확인 (본인만 삭제 가능)
        if (!targetUserId.equals(loginUserId)) {
            throw new IllegalArgumentException("본인 계정만 삭제할 수 있습니다.");
        }

        // DB에서 유저 존재 여부 확인 후 삭제
        User user = userRepository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 유저가 없습니다."));

        userRepository.delete(user);
    }
}

