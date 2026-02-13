package com.example.planner2.schedule.entity;


import com.example.planner2.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private String username; // 로그인 유저
    private Long userId;
    private String title;

    private String content;

    public Schedule(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    // 수정 메서드
    public void updateContent(String content) {
        this.content = content;
    }
}
