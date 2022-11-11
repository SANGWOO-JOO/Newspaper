package com.springboot.news.dto;

import lombok.Data;

@Data
public class CommentDto {

    private long id;

    private String name;
    private String content;

}
