package com.springboot.news.service;

import com.springboot.news.dto.ArticleDto;
import com.springboot.news.dto.ArticleResponse;


public interface ArticleService {
    //필드 : 반환 유형
    ArticleDto createArticle(ArticleDto articleDto);

    ArticleResponse getAllArticles(int pageNo, int pageSize, String sortBy, String sortDir);

    ArticleDto getArticleById(long id);

    ArticleDto updateArticle(ArticleDto articleDto, long id);

    void deleteArticleById(long id);

}
