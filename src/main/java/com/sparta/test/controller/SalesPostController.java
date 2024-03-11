package com.sparta.test.controller;

import com.sparta.test.dto.SalesPostRequestDto;
import com.sparta.test.dto.SalesPostResponseDto;
import com.sparta.test.service.SalesPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class SalesPostController {
    private final SalesPostService salesPostService;

    public SalesPostController(SalesPostService salesPostService) {
        this.salesPostService = salesPostService;
    }

    // 판매 게시글 생성
    @PostMapping
    public ResponseEntity<SalesPostResponseDto> createPost(@RequestBody SalesPostRequestDto requestDto) {
        SalesPostResponseDto responseDto = salesPostService.createPost(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<SalesPostResponseDto> getPostById(@PathVariable Long id) {
        SalesPostResponseDto responseDto = salesPostService.getPostById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<SalesPostResponseDto>> getAllPosts() {
        List<SalesPostResponseDto> responseDtos = salesPostService.getAllPosts();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<SalesPostResponseDto> updatePost(@PathVariable Long id, @RequestBody SalesPostRequestDto requestDto) {
        SalesPostResponseDto responseDto = salesPostService.updatePost(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        salesPostService.deletePost(id);
        return new ResponseEntity<>("삭제완료", HttpStatus.OK);
    }
}

