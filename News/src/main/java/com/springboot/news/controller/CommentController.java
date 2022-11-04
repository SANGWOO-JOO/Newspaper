package com.springboot.news.controller;

import com.springboot.news.payload.CommentDto;
import com.springboot.news.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable (value ="articleId") long articleId, @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(articleId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/articles/{articleId}/comments")
    public List<CommentDto> getCommentsByArticleId(@PathVariable(value = "articleId") Long articleId){
        return commentService.getCommentsByArticleId(articleId);
    }
}
