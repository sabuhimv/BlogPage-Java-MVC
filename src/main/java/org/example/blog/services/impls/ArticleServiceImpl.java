package org.example.blog.services.impls;

import org.example.blog.dtos.articledtos.ArticleCreatDto;
import org.example.blog.dtos.articledtos.ArticleDto;
import org.example.blog.models.Article;
import org.example.blog.models.Category;
import org.example.blog.repositories.ArticleRepository;
import org.example.blog.repositories.CategoryRepository;
import org.example.blog.services.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtoList = articleRepository.findAll().stream().map(article -> modelMapper.map(article, ArticleDto.class)).collect(Collectors.toList());
        return articleDtoList;
    }

    @Override
    public void addArticle(ArticleCreatDto articleCreatDto) {
        Article article = modelMapper.map(articleCreatDto,Article.class);
        Category category=categoryRepository.findById(articleCreatDto.getCategoryId()).get();
        Date date = new Date();
        article.setCreatedDate(date);
        article.setUpdatedDate(date);
        article.setCategory(category);
        articleRepository.save(article);
    }
}
