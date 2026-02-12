package com.example.planner2.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UpdateUserResponse {

    private final Long id;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public UpdateUserResponse(Long id, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.id = id;
        this.userName = userName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
