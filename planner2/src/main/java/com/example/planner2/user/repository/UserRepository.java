package com.example.planner2.user.repository;

import com.example.planner2.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository<엔티티, PK타입>
    // 로그인용 (이메일로 조회)
    Optional<User> findByEmail(String email);

    // username 중복 체크
    boolean existsByUsername(String username);

    // 이메일 중복 체크
    boolean existsByEmail(String email);
}