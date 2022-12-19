package com.springboot.news.entity;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp; // 타임 스탬프
    private String message; //메시지
    private String details; //세부 사항

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
