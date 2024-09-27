package org.example.blog.repositories;

import org.example.blog.dtos.articledtos.ArticleDto;
import org.example.blog.models.Article;
import org.example.blog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategory(Category category);
    @Query("SELECT u FROM Article u WHERE lower(u.title) like %?1% ")
    List<Article> findByTitleContainingIgnoreCase(String title);
}