package com.todayfriend.global.exception.handler;

import com.todayfriend.global.exception.CustomException;
import com.todayfriend.global.exception.dto.ExceptionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<ExceptionResponseDto> exception(CustomException e) {

        return ResponseEntity
                .status(e.getStatus())
                .body(ExceptionResponseDto.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }


}
