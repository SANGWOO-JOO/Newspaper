package com.springboot.news.service.impl;

import com.springboot.news.entity.Post;
import com.springboot.news.payload.PostDto;
import com.springboot.news.repository.PostRepository;
import com.springboot.news.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    //Repository 주입 : 사용하기 위해서
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 재정의
    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        //Postrepository.findAll() 메서드를 호출
        List<Post> posts = postRepository.findAll();
        /*
            Post 목록을 어떻게 dto로 변환
         */
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    // convert Entity into DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

}
