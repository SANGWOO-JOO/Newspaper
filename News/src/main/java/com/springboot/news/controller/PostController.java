package com.springboot.news.controller;


import com.springboot.news.payload.PostDto;
import com.springboot.news.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    /*
        클래스가 아닌 인터페이스를 주입하고 여기서 느슨한 결합을 하고있다.
        인터페이스를 사용하면 구현을 느슨한 결합으로 만들 수 있다.
     */
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

}
