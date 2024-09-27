package org.example.blog.controller;

import org.example.blog.dtos.articledtos.ArticleCreatDto;
import org.example.blog.dtos.articledtos.ArticleDto;
import org.example.blog.dtos.articledtos.ArticleUpdateDto;
import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.dtos.categorydtos.CategoryDto;
import org.example.blog.dtos.categorydtos.CategoryUpdateDto;
import org.example.blog.dtos.userdtos.UserDashboardListDto;
import org.example.blog.services.ArticleService;
import org.example.blog.services.CategoryService;
import org.example.blog.services.UserService;
import org.example.blog.services.impls.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.spring6.processor.SpringUErrorsTagProcessor;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private  ArticleService articleService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/admin/article/remove/{id}")
    public String removeArticle(@PathVariable Long id){
        articleService.removeArticle(id);
        return "redirect:/admin/article";
    }

    @GetMapping("/admin/article/update/{id}")
    public String updateArticle(@PathVariable Long id, Model model){
        ArticleUpdateDto articleUpdateDto = articleService.findUpdateArticle(id);
        List<CategoryDto> categories=categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("article",articleUpdateDto);
        return "/dashboard/article/update";
    }
    @PostMapping("/admin/article/update")
    public String updateArticle(@ModelAttribute ArticleUpdateDto articleUpdateDto){
        articleService.updateArticle(articleUpdateDto);
        return "redirect:/admin/article";
    }

//    CATEGORY
    @GetMapping("/admin/category/remove/{id}")
    public String removeCategory(@PathVariable Long id){
        categoryService.removeCategory(id);
        return "redirect:/admin/category";
    }
    @GetMapping("/admin/category/update/{id}")
    public String updateCategory(@PathVariable Long id, Model model){
        CategoryUpdateDto categoryUpdateDto=categoryService.findUpdateCategory(id);
        model.addAttribute("category",categoryUpdateDto);
        return "/dashboard/category/update";
    }
    @PostMapping("/admin/category/update")
    public String updateCategory(@ModelAttribute CategoryUpdateDto categoryUpdateDto){
        categoryService.updateCategory(categoryUpdateDto);
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model){
        List<UserDashboardListDto> userList = userService.getDashboardUsers();
        model.addAttribute("users",userList);
        return "/dashboard/auth/user-list";
    }
}
