package org.example.blog.dtos.articledtos;

import lombok.Getter;
import lombok.Setter;
import org.example.blog.dtos.categorydtos.CategoryDto;

import java.util.Date;


@Getter
@Setter
public class ArticleDetailDto {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;
    private int viewCount;

    private CategoryDto category;
}
