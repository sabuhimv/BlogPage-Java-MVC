package org.example.blog.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String subtitle;

    @Column(name = "description",  length = 10000)
    private String description;

    private String photoUrl;
    private Date createdDate;
    private Date updatedDate;
    private int viewCount;

//      Sehv usuldur.!!!
//    @Column(columnDefinition = "boolean default false")

    private Boolean isDeleted = false;

    private String seoUrl;


    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Tag> tags;
}
