package org.example.blog.controller;

import org.example.blog.dtos.articledtos.ArticleDetailDto;
import org.example.blog.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/detail/{id}/{seoUrl}")
    public String detail(@PathVariable Long id, Model model){
        ArticleDetailDto articleDetailDto = articleService.articleDetail(id);
        model.addAttribute("article", articleDetailDto);
        return "detail";
    }
}
