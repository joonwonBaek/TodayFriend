package com.todayfriend.global.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class CustomException extends RuntimeException {
    private HttpStatus status;
    private int code;
    private String message;

    public CustomException(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
