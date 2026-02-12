package com.example.planner2.schedule.service;

import com.example.planner2.schedule.dto.CreateScheduleRequest;
import com.example.planner2.schedule.dto.CreateScheduleResponse;
import com.example.planner2.schedule.dto.GetOneScheduleResponse;
import com.example.planner2.schedule.dto.UpdateScheduleRequest;
import com.example.planner2.schedule.entity.Schedule;
import com.example.planner2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request, String username) {

        // 세션에서 로그인된 유저인지 확인하는 로직은 컨트롤러에서 처리 가능
        Schedule schedule = new Schedule(
                username,
                request.getTitle(),
                request.getContent(),
                request.getCreatedAt() != null ? request.getCreatedAt() : LocalDateTime.now()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 단 건 조회
    public GetOneScheduleResponse getSchedule(Long id, String username) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        if (!schedule.getUsername().equals(username)) {
            throw new IllegalArgumentException("조회 권한이 없습니다.");
        }

        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 전체 조회
    public List<CreateScheduleResponse> getAllSchedules(String username) {
        return scheduleRepository.findAllByUsername(username)
                .stream()
                .map(s -> new CreateScheduleResponse(
                        s.getId(),
                        s.getUsername(),
                        s.getTitle(),
                        s.getContent(),
                        s.getCreatedAt(),
                        s.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }

    // 수정
    @Transactional
    public CreateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request, String username) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        if (!schedule.getUsername().equals(username)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        schedule.updateContent(request.getContent());
        Schedule updated = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                updated.getId(),
                updated.getUsername(),
                updated.getTitle(),
                updated.getContent(),
                updated.getCreatedAt(),
                updated.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public String deleteSchedule(Long id, String username) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        if (!schedule.getUsername().equals(username)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }
        scheduleRepository.delete(schedule);
        return "일정 삭제가 완료 됐습니다.";
    }
}