package org.example.blog.services;

import org.example.blog.dtos.articledtos.ArticleCreatDto;
import org.example.blog.dtos.articledtos.ArticleDto;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> getArticles();
    void addArticle(ArticleCreatDto articleCreatDto);
}
