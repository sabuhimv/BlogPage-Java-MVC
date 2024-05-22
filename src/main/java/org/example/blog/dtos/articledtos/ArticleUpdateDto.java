package org.example.blog.dtos.articledtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleUpdateDto {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private String photoUrl;
    private Long categoryId;
}
