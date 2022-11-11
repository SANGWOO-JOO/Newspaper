package com.springboot.news.service.impl;

import com.springboot.news.entity.Article;
import com.springboot.news.entity.Comment;
import com.springboot.news.exception.NewsAPIException;
import com.springboot.news.exception.ResourceNotFoundException;
import com.springboot.news.dto.CommentDto;
import com.springboot.news.repository.ArticleRepository;
import com.springboot.news.repository.CommentRepository;
import com.springboot.news.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;

    public CommentServiceImpl(CommentRepository commentRepository,ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository= articleRepository;
    }

    @Override
    public CommentDto createComment(long articleId, CommentDto commentDto) {

        Comment comment =mapToEntity(commentDto);
        // ARTICLE 엔티티 검색
        Article article =articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        // 댓글 작성
        comment.setArticle(article);

        //댓글 객체 DB에 저장
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByArticleId(long articleId) {
        // articleId 및 목록으로 검색하여 가져오기
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long articleId, Long commentId) {

        // ARTICLE 엔티티 검색
        Article article =articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        //댓글 검색
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getArticle().getId().equals(article.getId())){
            throw new NewsAPIException(HttpStatus.BAD_REQUEST, "댓글이 뉴스 기사에 없습니다.");
        }

        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long articleId, long commentId, CommentDto commentRequest) {
        // ARTICLE 엔티티 검색
        Article article =articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));


        //댓글 검색
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getArticle().getId().equals(article.getId())){
            throw new NewsAPIException(HttpStatus.BAD_REQUEST ,"댓글이 뉴스 기사에 없습니다.");
        }

        comment.setName(commentRequest.getName());
        comment.setContent(commentRequest.getContent());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long articleId, Long commentId) {
        // ARTICLE 엔티티 검색
        Article article =articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article", "id", articleId));

        //댓글 검색
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getArticle().getId().equals(article.getId())){
            throw new NewsAPIException(HttpStatus.BAD_REQUEST ,"댓글이 뉴스 기사에 없습니다.");
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDTO(Comment comment){

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setContent(comment.getContent());
        return  commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){

        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setContent(commentDto.getContent());
        return  comment;
    }
}
