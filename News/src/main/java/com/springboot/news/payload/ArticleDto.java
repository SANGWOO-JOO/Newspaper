package com.springboot.news.payload;


import lombok.Data;

@Data
public class ArticleDto {
    private long id;
    private String title;
    private String description;
    private String content;
}
