package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized Exception"),
    INVALID_KEY(500, HttpStatus.BAD_REQUEST, "Invalid error code key"),
    USER_EXISTED(1002, HttpStatus.BAD_REQUEST, "User already existed"),
    INVALID_USERNAME(1003, HttpStatus.BAD_REQUEST, "Username must be at least {min} characters length."),
    INVALID_PASSWORD(1004, HttpStatus.BAD_REQUEST, "Password must be at least {min} characters length."),
    USER_NOT_EXISTED(1005, HttpStatus.NOT_FOUND, "User not existed"),
    UNAUTHENTICATED(1006, HttpStatus.UNAUTHORIZED, "Unauthenticated"),
    UNAUTHORIZED(1007, HttpStatus.FORBIDDEN, "Unauthorized"),

    INVALID_DOB(1008, HttpStatus.BAD_REQUEST, "Your must be at least {min} years old."),
    ;

    private final HttpStatusCode statusCode;

    private final int code;
    private final String message;
    ErrorCode(int code, HttpStatusCode httpStatusCode, String message) {
        this.code = code;
        this.message = message;
        this.statusCode = httpStatusCode;
    }
}
