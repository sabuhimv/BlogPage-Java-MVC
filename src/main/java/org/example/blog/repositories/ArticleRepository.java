package org.example.blog.repositories;

import org.example.blog.dtos.articledtos.ArticleDto;
import org.example.blog.models.Article;
import org.example.blog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);
}