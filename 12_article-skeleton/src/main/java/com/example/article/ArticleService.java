package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleDto createArticle(ArticleDto dto) {
//        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setWriter(dto.getWriter());
        return ArticleDto.fromEntity(repository.save(newArticle));
    }

    public ArticleDto readArticle(Long id) {
//        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        Optional<ArticleEntity> optionalArticle = repository.findById(id);
        // 채워 넣어 보기
        // optional 안에 Article 이 들어있으면
        if (optionalArticle.isPresent())
            // DTO로 전환 후 반환
            return ArticleDto.fromEntity(optionalArticle.get());
        // 아니면 404
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

//        // 반대 순서
//        if (optionalArticle.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        return ArticleDto.fromEntity(optionalArticle.get());
    }

    public List<ArticleDto> readArticleAll() {
//        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        List<ArticleDto> articleList = new ArrayList<>();
        for (ArticleEntity entity: repository.findAll()) {
            articleList.add(ArticleDto.fromEntity(entity));
        }
        return articleList;
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
//        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        // UPDATE
        // ArticleEntity 받아오기
        Optional<ArticleEntity> optionalArticle = repository.findById(id);
        // ArticleEntity 가 있을 때
        if (optionalArticle.isPresent()) {
            // 전달 받은 dto 기준으로 수정
            ArticleEntity article = optionalArticle.get();
            article.setWriter(dto.getWriter());
            article.setTitle(dto.getTitle());
            article.setContent(dto.getContent());
            repository.save(article);
            return ArticleDto.fromEntity(article);
        }
        // 아니면 404
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteArticle(Long id) {
//        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        if (repository.existsById(id))
            repository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

//        // 존재하면
//        Optional<ArticleEntity> optionalArticle = repository.findById(id);
//        if (optionalArticle.isPresent())
//            // 삭제
//            repository.deleteById(id);
//        // 아니면 404
//        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

//        // 정보 제공 없이 실패하면 조용히 실패
//        repository.deleteById(id);
    }

//    public List<ArticleDto> readArticlePaged() {
//        // JPA Query Method 방식 (비추)
//        List<ArticleDto> articleDtoList = new ArrayList<>();
//        for (ArticleEntity entity: repository.findTop20ByOrderByIdDesc()) {
//            articleDtoList.add(ArticleDto.fromEntity(entity));
//        }
//        return articleDtoList;
//    }

//    public List<ArticleDto> readArticlePaged() {
//        // PagingAndSortingRepository 메서드에 전달하는 용도
//        // 조회하고 싶은 페이지의 정보를 담는 객체
//        // 20개씩 데이터를 나눌 때 0번 페이지(첫 페이지)를 달라고 요청하는 Pageable
//        Pageable pageable = PageRequest.of(
//                0, 20, Sort.by("id").descending());
//        Page<ArticleEntity> articleEntityPage = repository.findAll(pageable);
//
//        List<ArticleDto> articleDtoList = new ArrayList<>();
//        for (ArticleEntity entity: articleEntityPage) {
//            articleDtoList.add(ArticleDto.fromEntity(entity));
//        }
//        return articleDtoList;
//    }

    public Page<ArticleDto> readArticlePaged(
            Integer pageNumber, Integer pageSize
    ) {
        // PagingAndSortingRepository 메서드에 전달하는 용도
        // 조회하고 싶은 페이지의 정보를 담는 객체
        // 20개씩 데이터를 나눌 때 0번 페이지(첫 페이지)를 달라고 요청하는 Pageable
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize, Sort.by("id").descending());
        Page<ArticleEntity> articleEntityPage = repository.findAll(pageable);
        // map: 전달받은 함수를 각 원소에 인자로 전달한 결과를 다시 모아서 Stream 으로
        // Page.map: 전달받은 함수를 각 원소에 인자로 전달한 결과를 다시 모아서 Page 로
        Page<ArticleDto> articleDtoPage = articleEntityPage.map(ArticleDto::fromEntity);
        return articleDtoPage;
    }

    public Page<ArticleDto> search(
            String query, Integer pageNumber
    ) {
        Pageable pageable = PageRequest.of(
                pageNumber, 20, Sort.by("id").descending());
        return repository.findAllByTitleContains(query, pageable).map(ArticleDto::fromEntity);
    }
}
