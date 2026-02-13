package com.example.planner2.schedule.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {

    private final Long id;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public UpdateScheduleResponse(Long id, Long userId, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
