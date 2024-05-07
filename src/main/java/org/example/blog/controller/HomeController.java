package org.example.blog.controller;

import org.example.blog.dtos.categorydtos.CategoryCreateDto;
import org.example.blog.services.CategoryService;
import org.example.blog.services.impls.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(){
        CategoryCreateDto categoryCreateDto=new CategoryCreateDto();
        categoryCreateDto.setName("texnology");
        categoryService.add(categoryCreateDto);

        return "home";
    }
    @PostMapping("/")
    public String index(@ModelAttribute CategoryCreateDto categoryCreateDto){
        categoryService.add(categoryCreateDto);
        return "home";
    }
}
