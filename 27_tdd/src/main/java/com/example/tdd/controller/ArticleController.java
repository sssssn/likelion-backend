package com.example.tdd.controller;

import com.example.tdd.dto.ArticleDto;
import com.example.tdd.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<ArticleDto> readArticles(
            @RequestParam("title") String title
    ) {
        return articleService.findByTitle(title);
    }
}
