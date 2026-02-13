package com.example.planner2.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneScheduleResponse {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public GetOneScheduleResponse(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
