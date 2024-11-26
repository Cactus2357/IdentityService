package com.example.demo.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    INVALID_KEY(500, "Invalid error code key"),
    USER_EXISTED(1002, "User already existed"),
    INVALID_USERNAME(1003, "Username must be at least 3 characters length."),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters length."),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
