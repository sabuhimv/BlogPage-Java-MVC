package org.example.blog.services;

import org.example.blog.dtos.articledtos.ArticleUpdateDto;
import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.dtos.categorydtos.CategoryDto;
import org.example.blog.dtos.categorydtos.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
    void add(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAllCategories();
    void removeCategory(Long id);
    CategoryUpdateDto findUpdateCategory(Long id);
    void updateCategory(CategoryUpdateDto categoryUpdateDto);
}
