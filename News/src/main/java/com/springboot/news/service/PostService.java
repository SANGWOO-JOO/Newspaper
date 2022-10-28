package com.springboot.news.service;

import com.springboot.news.payload.PostDto;

import java.util.List;


public interface PostService {
    //필드 : 반환 유형
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

}
