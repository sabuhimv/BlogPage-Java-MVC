package org.example.blog.services.impls;

import org.example.blog.dtos.articledtos.*;
import org.example.blog.dtos.categorydtos.CategoryDto;
import org.example.blog.helpers.SeoHelper;
import org.example.blog.models.Article;
import org.example.blog.models.Category;
import org.example.blog.repositories.ArticleRepository;
import org.example.blog.repositories.CategoryRepository;
import org.example.blog.services.ArticleService;
import org.example.blog.services.EmailService;
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

    @Autowired
    private EmailService emailService;


    @Override
    public List<ArticleDto> getArticles() {
        List<ArticleDto> articleDtoList=articleRepository.findAll().stream()
                .filter(x->x.getIsDeleted()==false)
                .map(article -> modelMapper.map(article,ArticleDto.class))
                .collect(Collectors.toList());
        return articleDtoList;
    }

    @Override
    public List<ArticleHomeDto> getHomeArticles() {
        List<ArticleHomeDto> articleHomeDtos = articleRepository.findAll().stream()
                .filter(x->x.getIsDeleted() == false)
                .map(article -> modelMapper.map(article, ArticleHomeDto.class))
                .collect(Collectors.toList());

        return articleHomeDtos;
    }

    @Override
    public List<ArticleHomeDto> getHomeSearchArticles(String text) {
        List<Article> articles = articleRepository.findByTitleContainingIgnoreCase(text);
        List<ArticleHomeDto> articleHomeDtos = articles.stream()
                .filter(x->x.getIsDeleted() == false)
                .map(article -> modelMapper.map(article,ArticleHomeDto.class))
                .collect(Collectors.toList());
        return articleHomeDtos;
    }

    @Override
    public void addArticle(ArticleCreatDto articleCreatDto) {
        Article article = modelMapper.map(articleCreatDto,Article.class);
        Category category=categoryRepository.findById(articleCreatDto.getCategoryId()).get();
        SeoHelper seoHelper = new SeoHelper();
        article.setSeoUrl(seoHelper.seoUrlHelper(articleCreatDto.getTitle()));
        Date date = new Date();
        article.setCreatedDate(date);
        article.setUpdatedDate(date);
        article.setCategory(category);
//        article.setIsDeleted(false);
        articleRepository.save(article);
    }

    @Override
    public void updateArticle(ArticleUpdateDto articleUpdateDto) {
        Article findArticle=articleRepository.findById(articleUpdateDto.getId()).orElseThrow();
        Category category = categoryRepository.findById(articleUpdateDto.getCategoryId()).orElseThrow();
        SeoHelper seoHelper = new SeoHelper();
        findArticle.setSeoUrl(seoHelper.seoUrlHelper(articleUpdateDto.getTitle()));

        findArticle.setId(articleUpdateDto.getId());
        findArticle.setTitle(articleUpdateDto.getTitle());
        findArticle.setDescription(articleUpdateDto.getDescription());
        findArticle.setUpdatedDate(new Date());
        findArticle.setPhotoUrl(articleUpdateDto.getPhotoUrl());
        findArticle.setCategory(category);
        articleRepository.saveAndFlush(findArticle);
    }

    @Override
    public ArticleUpdateDto findUpdateArticle(Long id) {
        Article article=articleRepository.findById(id).orElseThrow();
        ArticleUpdateDto articleUpdateDto=modelMapper.map(article, ArticleUpdateDto.class);

        return articleUpdateDto;
    }

    @Override
    public void removeArticle(Long articleId) {
        Article article=articleRepository.findById(articleId).orElseThrow();
        article.setIsDeleted(true);
        articleRepository.save(article);
    }

    @Override
    public ArticleDetailDto articleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        ArticleDetailDto articleDetailDto=modelMapper.map(article,ArticleDetailDto.class);
        return articleDetailDto;
    }
}
