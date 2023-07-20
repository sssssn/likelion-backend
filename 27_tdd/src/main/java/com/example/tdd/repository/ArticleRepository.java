package com.example.tdd.repository;

import com.example.tdd.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository
        extends JpaRepository<Article, Long> {
    List<Article> findAllByTitleContains(String title);
}
