package org.example.blog.services;

import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.dtos.categorydtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    void add(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAllCategories();
}
