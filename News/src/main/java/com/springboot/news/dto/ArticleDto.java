package com.springboot.news.dto;


import lombok.Data;

import java.util.Set;

@Data
public class ArticleDto {
    private long id;
    private String title;
    private String section;
    private String content;

    private Set<CommentDto> comments;
}
