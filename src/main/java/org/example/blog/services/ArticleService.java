package org.example.blog.services;

import org.example.blog.dtos.articledtos.*;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getArticles();
    List<ArticleHomeDto> getHomeArticles();
    void addArticle(ArticleCreatDto articleCreatDto);
    void updateArticle(ArticleUpdateDto articleUpdateDto);
    ArticleUpdateDto findUpdateArticle(Long id);
    void removeArticle(Long articleId);
    ArticleDetailDto articleDetail(Long id);
}
