package com.springboot.news.repository;

import com.springboot.news.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //
}
