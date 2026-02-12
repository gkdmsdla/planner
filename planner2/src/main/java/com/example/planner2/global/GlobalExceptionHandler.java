package com.example.planner2.global;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice // @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
// 예외를 전역으로 처리하는 클래스, Global = 전역, ExceptionHandler = 예외 처리기

    @ExceptionHandler(IllegalArgumentException.class)
    // IllegalArgumentException 예외가 발생하면 아래 메서드가 대신 처리하라는 뜻
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException e) {
    // ResponseEntity<Map<String, String>> : HTTP 응답을 직접 만들어서 보내겠다, <Map<String, String>> : JSON 형태로 보낼 데이터
    // handleIllegalArgument : 메서드 이름
        return ResponseEntity
                .badRequest() // HTTP 상태코드 400 반환
                .body(Map.of("error", e.getMessage()));
                // Map.of("error", e.getMessage()):  "error": "이미 사용 중인 이메일입니다."
    }
}
