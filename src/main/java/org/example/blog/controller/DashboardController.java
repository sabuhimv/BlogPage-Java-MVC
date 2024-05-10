package org.example.blog.controller;

import org.example.blog.dtos.articledtos.ArticleCreatDto;
import org.example.blog.dtos.articledtos.ArticleDto;
import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.dtos.categorydtos.CategoryDto;
import org.example.blog.services.ArticleService;
import org.example.blog.services.CategoryService;
import org.example.blog.services.impls.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private  ArticleService articleService;

    @GetMapping("/admin")
    public String index(){
        return "/dashboard/home";
    }

    @GetMapping("/admin/category")
    public String category(Model model){
        List<CategoryDto> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/category";
    }
    @GetMapping("/admin/category/category-create")
    public String addCategory(){
        return "/dashboard/category-create";
    }
    @PostMapping("/admin/category/category-create")
    public String addCategory(@ModelAttribute CategoryCreateDto categoryCreateDto){
        categoryService.add(categoryCreateDto);
        return "redirect:/admin/category";
    }


    @GetMapping("/admin/article")
    public String article(Model model){
        List<ArticleDto> articles=articleService.getArticles();
        model.addAttribute("articles",articles);
        return "/dashboard/article";
    }

    @GetMapping("/admin/article/create")
    public String articleCreate(Model model){
        List<CategoryDto> categories=categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "/dashboard/article-create";
    }
    @PostMapping("/admin/article/create")
    public String articleCreate(@ModelAttribute ArticleCreatDto articleCreatDto){
        articleService.addArticle(articleCreatDto);
        return "redirect:/admin/article";
    }
}
