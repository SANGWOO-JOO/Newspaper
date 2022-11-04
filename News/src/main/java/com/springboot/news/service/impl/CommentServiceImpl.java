package com.springboot.news.service.impl;

import com.springboot.news.entity.Article;
import com.springboot.news.entity.Comment;
import com.springboot.news.exception.ResourceNotFoundException;
import com.springboot.news.payload.CommentDto;
import com.springboot.news.repository.ArticleRepository;
import com.springboot.news.repository.CommentRepository;
import com.springboot.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CommentDto> getCommentsByArticleId(long articleID) {
      // articleId 및 목록으로 검색하여 가져오기
      List<Comment> comments = commentRepository.findByArticleId(articleID);

      return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
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
