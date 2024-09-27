package org.example.blog.controller;

import org.example.blog.dtos.articledtos.ArticleDetailDto;
import org.example.blog.dtos.articledtos.ArticleDto;
import org.example.blog.dtos.articledtos.ArticleHomeDto;
import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.services.ArticleService;
import org.example.blog.services.CategoryService;
import org.example.blog.services.impls.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model){
        List<ArticleHomeDto> articles= articleService.getHomeArticles();
        model.addAttribute("articles",articles);
        return "home";
    }

    @PostMapping("/")
    public String index(String searchTerm,Model model){
        List<ArticleHomeDto> articles= articleService.getHomeSearchArticles(searchTerm);
        model.addAttribute("articles",articles);
        return "home";
    }
}
