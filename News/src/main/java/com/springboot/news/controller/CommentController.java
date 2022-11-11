package com.springboot.news.controller;

import com.springboot.news.dto.CommentDto;
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

    @GetMapping("/articles/{articleId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value ="articleId") Long articleId, @PathVariable(value = "id") Long commentId){

        CommentDto commentDto =commentService.getCommentById(articleId, commentId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    @PutMapping("/articles/{articleId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "articleId") Long articleId,@PathVariable(value = "id") Long commentId, @RequestBody CommentDto commentDto ){

        CommentDto updatedComment = commentService.updateComment(articleId,commentId,commentDto );

        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @DeleteMapping("/articles/{articleId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "articleId") Long articleId,@PathVariable(value = "id") Long commentId){

        commentService.deleteComment(articleId, commentId);

        return new ResponseEntity<>("댓글이 삭제되었습니다.",HttpStatus.OK);
    }
}
