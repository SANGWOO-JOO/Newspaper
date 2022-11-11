package com.springboot.news.dto;


import lombok.Data;

@Data
public class ArticleDto {
    private long id;
    private String title;
    private String section;
    private String content;
}
