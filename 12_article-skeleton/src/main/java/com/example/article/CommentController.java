package com.example.article;

import com.example.article.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    // 게시글 작성
    // POST /articles/{articleId}/comments
    @PostMapping
    public CommentDto create(
            @PathVariable("articleId") Long articleId,
            @RequestBody CommentDto dto
    ) {
        return service.createComment(articleId, dto);
    }

    // 게시글 댓글 전체 조회
    // GET /articles/{articleId}/comments
    // 반환값 인자 및 필요 어노테이션
    @GetMapping
    public List<CommentDto> readAll(
            @PathVariable("articleId") Long articleId
    ) {
        return service.readCommentAll(articleId);
    }

    // 게시글 댓글 수정
    // PUT /articles/{articleId}/comments/{commentId}
    // RequestMapping 부터 return 까지
    @PutMapping("/{commentId}")
    public CommentDto update(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto dto
    ) {
        return service.updateComment(articleId, commentId, dto);
    }

    // 게시글 댓글 삭제
    // DELETE /articles/{articleId}/comments/{commentId}
    @DeleteMapping("/{commentId}")
    public void delete(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId
    ) {
        service.deleteComment(articleId, commentId);
    }
}
