package com.springboot.news.service;

import com.springboot.news.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long articleId, CommentDto commentDto);

    List<CommentDto> getCommentsByArticleId(long articleID);

}
