package org.example.blog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private Boolean isDeleted = false;

    @OneToMany
    @JoinColumn(name = "articles",nullable = true) // Bu annotasiya Articles cədvəlində Category cədvəlinin əsas açarına istinad edən xarici açar sütununu təyin edir. Sütunun adı "məqalələr" olaraq təyin edilib və o, null dəyərlərə icazə verir (məqalədə kateqoriya təyin olunmaya bilər).
    private List<Article> articles;
}
