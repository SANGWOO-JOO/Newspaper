package com.springboot.news.exception;

import org.springframework.http.HttpStatus;

public class NewsAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public NewsAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public NewsAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
