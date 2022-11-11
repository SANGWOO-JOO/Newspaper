package com.springboot.news.service;

import com.springboot.news.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long articleId, CommentDto commentDto);

    List<CommentDto> getCommentsByArticleId(long articleId);

    CommentDto getCommentById(Long articleId, Long commentId);

    CommentDto updateComment(Long articleId ,long commentId, CommentDto commentRequest);

    void deleteComment(Long articleId, Long commentId );
}
