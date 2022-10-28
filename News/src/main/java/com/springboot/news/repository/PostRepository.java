package com.springboot.news.repository;

import com.springboot.news.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    //
}
