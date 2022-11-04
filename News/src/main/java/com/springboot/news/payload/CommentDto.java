package com.springboot.news.payload;

import lombok.Data;

@Data
public class CommentDto {

    private long id;

    private String name;
    private String content;

}
