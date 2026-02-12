package com.example.planner2.schedule.entity;


import com.example.planner2.user.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 매개변수가 없는 생성자 만듦 (생성자의 접근 제한자를 protected로 설정)


public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue: 이 값은 자동으로 생성, strategy = GenerationType.IDENTITY: DB가 알아서 번호를 1, 2, 3, 4 자동 증가
    private Long id;

    @Column(length = 10, nullable = false) // @Column: 컬럼 옵션을 조정
    private String title;





}
