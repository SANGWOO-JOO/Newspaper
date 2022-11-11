package com.springboot.news.service.impl;

import com.springboot.news.entity.Article;
import com.springboot.news.exception.ResourceNotFoundException;
import com.springboot.news.dto.ArticleDto;
import com.springboot.news.dto.ArticleResponse;
import com.springboot.news.repository.ArticleRepository;
import com.springboot.news.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    //Repository 주입 : 사용하기 위해서
    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 재정의
    @Override
    public ArticleDto createArticle(ArticleDto articleDto) {

        // convert DTO to entity
        Article article = mapToEntity(articleDto);
        Article newArticle = articleRepository.save(article);

        // convert entity to DTO
        ArticleDto articleResponse = mapToDTO(newArticle);
        return articleResponse;
    }

    @Override
    public ArticleResponse getAllArticles(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Article> articles = articleRepository.findAll(pageable);

        //Postrepository.findAll() 메서드를 호출
        List<Article> listOfArticles = articles.getContent();

        List<ArticleDto> content = listOfArticles.stream().map(article -> mapToDTO(article)).collect(Collectors.toList());

        ArticleResponse articleResponse =new ArticleResponse();
        articleResponse.setContent(content);
        articleResponse.setPageNo(articles.getNumber());
        articleResponse.setPageSize(articles.getSize());
        articleResponse.setTotalElements(articles.getTotalElements());
        articleResponse.setTotalPages(articles.getTotalPages());
        articleResponse.setLast(articles.isLast());

        return articleResponse;
    }


    @Override
    public ArticleDto getArticleById(long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(article);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto, long id) {
        // get post by id from the database
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        article.setTitle(articleDto.getTitle());
        article.setSection(articleDto.getSection());
        article.setContent(articleDto.getContent());

        Article updatedArticle = articleRepository.save(article);
        return mapToDTO(updatedArticle);
    }

    @Override
    public void deleteArticleById(long id) {
        // get post by id from the database
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        articleRepository.delete(article);
    }

    // convert Entity into DTO
    private ArticleDto mapToDTO(Article article){
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setSection(article.getSection());
        articleDto.setContent(article.getContent());
        return articleDto;
    }

    // convert DTO to entity
    private Article mapToEntity(ArticleDto articleDto){
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setSection(articleDto.getSection());
        article.setContent(articleDto.getContent());
        return article;
    }

}
