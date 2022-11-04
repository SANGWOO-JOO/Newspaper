package com.springboot.news.controller;


import com.springboot.news.payload.ArticleDto;
import com.springboot.news.payload.ArticleResponse;
import com.springboot.news.service.ArticleService;
import com.springboot.news.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    /*
        클래스가 아닌 인터페이스를 주입하고 여기서 느슨한 결합을 하고있다.
        인터페이스를 사용하면 구현을 느슨한 결합으로 만들 수 있다.
     */
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ArticleDto> createPost(@RequestBody ArticleDto articleDto){
        return new ResponseEntity<>(articleService.createArticle(articleDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ArticleResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue =AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

    ){
        return articleService.getAllArticles(pageNo,pageSize,sortBy,sortDir);
    }

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    // update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> updatePost(@RequestBody ArticleDto articleDto, @PathVariable(name = "id") long id){

        ArticleDto articleResponse = articleService.updateArticle(articleDto, id);

        return new ResponseEntity<>(articleResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){

        articleService.deleteArticleById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}
