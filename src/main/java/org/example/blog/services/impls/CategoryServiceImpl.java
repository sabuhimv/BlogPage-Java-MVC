package org.example.blog.services.impls;

import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.dtos.categorydtos.CategoryDto;
import org.example.blog.models.Category;
import org.example.blog.repositories.CategoryRepository;
import org.example.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void add(CategoryCreateDto categoryCreateDto) {
        Category category = modelMapper.map(categoryCreateDto,Category.class);
        categoryRepository.save(category);
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories=categoryRepository.findAll().stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categories;
    }
}