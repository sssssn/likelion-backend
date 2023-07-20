package com.example.tdd;

import com.example.tdd.entity.Article;
import com.example.tdd.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    // articleRepository 를 생성하여 데이터베이스 소통의 창구 마련
    public void setArticleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void createArticle() {
        // given
        Article article = new Article();
        article.setTitle("Hello!");
        article.setContent("Tdd is hard T_T");

        // when
        article = articleRepository.save(article);

        // then
        assertThat(article).isNotNull();
        assertThat(article.getId()).isNotNull();
        assertEquals("Hello!", article.getTitle());
        assertEquals("Tdd is hard T_T", article.getContent());
    }

    @Test
    public void findByTitleContains() {
        // given
        Article article = new Article();
        article.setTitle("Hello!");
        articleRepository.save(article);
        article = new Article();
        article.setTitle("Yellow");
        articleRepository.save(article);

        // when
        List<Article> result1
                = articleRepository.findAllByTitleContains("ell");
        List<Article> result2
                = articleRepository.findAllByTitleContains("He");
        List<Article> result3
                = articleRepository.findAllByTitleContains("A");

        // then
        assertEquals(2, result1.size());
        assertEquals(1, result2.size());
        assertEquals(0, result3.size());
    }
}
