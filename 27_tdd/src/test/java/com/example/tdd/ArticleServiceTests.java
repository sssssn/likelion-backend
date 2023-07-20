package com.example.tdd;

import com.example.tdd.dto.ArticleDto;
import com.example.tdd.entity.Article;
import com.example.tdd.repository.ArticleRepository;
import com.example.tdd.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTests {
    @InjectMocks
    private ArticleService articleService;
    @Mock
    private ArticleRepository articleRepository;

    @Test
    // 제목의 유사도를 통해 조회
    public void searchByTitle() {
        // given
        String query = "Title";
        Article found = new Article();
        found.setTitle("Title here");
        found.setContent("Test Content");
        // mock
        when(articleRepository.findAllByTitleContains(query))
                .thenReturn(Collections.singletonList(found));

        // when
        // ArticleService 가 어떻게 동작하면 하는지
        List<ArticleDto> articleDtoList
                = articleService.findByTitle(query);

        // then
        assertEquals(1, articleDtoList.size());
        assertTrue(articleDtoList.get(0).getTitle().contains(query));
    }
}
