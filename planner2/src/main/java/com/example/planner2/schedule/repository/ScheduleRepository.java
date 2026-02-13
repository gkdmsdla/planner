package com.example.planner2.schedule.repository;

import com.example.planner2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 로그인된 유저의 모든 일정 조회
    List<Schedule> findAllByUserId(Long userId);
}
