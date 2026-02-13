package com.example.planner2.schedule.controller;

import com.example.planner2.auth.dto.SessionUser;
import com.example.planner2.schedule.dto.CreateScheduleRequest;
import com.example.planner2.schedule.dto.CreateScheduleResponse;
import com.example.planner2.schedule.dto.GetOneScheduleResponse;
import com.example.planner2.schedule.dto.UpdateScheduleRequest;
import com.example.planner2.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final HttpSession session;

    // 로그인된 유저 가져오기
    private String getLoginUser() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("loginUser");
        String username = sessionUser.getUserName();
        if (username == null) throw new IllegalStateException("로그인이 필요합니다.");
        return username;
    }

    // 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> create(@Validated @RequestBody CreateScheduleRequest request) {
        String username = getLoginUser();
        CreateScheduleResponse response = scheduleService.createSchedule(request, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetOneScheduleResponse> get(@PathVariable Long id) {
        String username = getLoginUser();
        return ResponseEntity.ok(scheduleService.getSchedule(id, username));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<CreateScheduleResponse>> getAll() {
        String username = getLoginUser();
        return ResponseEntity.ok(scheduleService.getAllSchedules(username));
    }

    // 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CreateScheduleResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody UpdateScheduleRequest request) {
        String username = getLoginUser();
        return ResponseEntity.ok(scheduleService.updateSchedule(id, request, username));
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String username = getLoginUser();
        String message = scheduleService.deleteSchedule(id, username);
        return ResponseEntity.ok(message);
    }
}

