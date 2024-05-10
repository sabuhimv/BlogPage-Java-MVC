package org.example.blog.dtos.articledtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreatDto {
    private String title;
    private String subtitle;
    private String description;
    private String photoUrl;
    private Long categoryId;
//    private Category category;
}