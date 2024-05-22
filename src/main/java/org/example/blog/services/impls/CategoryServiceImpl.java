package org.example.blog.services.impls;

import org.example.blog.dtos.articledtos.ArticleHomeDto;
import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.dtos.categorydtos.CategoryDto;
import org.example.blog.dtos.categorydtos.CategoryUpdateDto;
import org.example.blog.models.Article;
import org.example.blog.models.Category;
import org.example.blog.repositories.ArticleRepository;
import org.example.blog.repositories.CategoryRepository;
import org.example.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll().stream()
                .filter(x -> x.getIsDeleted() == false)
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }

    @Transactional
    @Override
    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
//        articleRepository.deleteByCategory(category);
//        categoryRepository.delete(category);
        category.setIsDeleted(true);
        List<Article> articles = articleRepository.findByCategory(category);
        for (Article article:articles){
            article.setIsDeleted(true);
            articleRepository.save(article);
        }
        categoryRepository.save(category);
    }

    @Override
    public CategoryUpdateDto findUpdateCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Don't Found Category"));
        CategoryUpdateDto categoryUpdateDto = modelMapper.map(category, CategoryUpdateDto.class);
        return categoryUpdateDto;
    }

    @Override
    public void updateCategory(CategoryUpdateDto categoryUpdateDto) {
        Category findCategory = categoryRepository.findById(categoryUpdateDto.getId()).orElseThrow();
        findCategory.setId(categoryUpdateDto.getId());
        findCategory.setName(categoryUpdateDto.getName());
        categoryRepository.saveAndFlush(findCategory);
    }
}