package com.sparta.test.service;

import com.sparta.test.dto.SalesPostRequestDto;
import com.sparta.test.dto.SalesPostResponseDto;
import com.sparta.test.entity.SalesPost;
import com.sparta.test.repositpry.SalesPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesPostService {
    private final SalesPostRepository salesPostRepository;

    @Autowired
    public SalesPostService(SalesPostRepository salesPostRepository) {
        this.salesPostRepository = salesPostRepository;
    }

    // 판매 게시글 생성
    public SalesPostResponseDto createPost(SalesPostRequestDto requestDto) {
        SalesPost entity = new SalesPost(requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice(), requestDto.getUsername());
        entity = salesPostRepository.save(entity);
        return new SalesPostResponseDto(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getUsername(), entity.getContent());
    }

    // 판매 게시글 상세 조회
    public SalesPostResponseDto getPostById(Long id) {
        Optional<SalesPost> optionalPost = salesPostRepository.findById(id);
        if (optionalPost.isPresent()) {
            SalesPost entity = optionalPost.get();
            return new SalesPostResponseDto(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getUsername(), entity.getContent());
        } else {
            throw new IllegalArgumentException("해당 ID의 게시글을 찾을 수 없습니다: " + id);
        }
    }

    // 판매 게시글 전체 조회
    public List<SalesPostResponseDto> getAllPosts() {
        List<SalesPost> entities = salesPostRepository.findAll();
        List<SalesPostResponseDto> dtos = new ArrayList<>();
        for (SalesPost entity : entities) {
            dtos.add(new SalesPostResponseDto(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getUsername(), entity.getContent()));
        }
        return dtos;
    }

    // 판매 게시글 수정
    public SalesPostResponseDto updatePost(Long id, SalesPostRequestDto requestDto) {
        Optional<SalesPost> optionalPost = salesPostRepository.findById(id);
        if (optionalPost.isPresent()) {
            SalesPost entity = optionalPost.get();
            // ID를 설정하지 않고 엔터티를 생성합니다.
            SalesPost updatedEntity = new SalesPost(requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice(), requestDto.getUsername());
            updatedEntity.setId(id); // ID 설정
            // 업데이트된 엔터티를 저장합니다.
            entity = salesPostRepository.save(updatedEntity);
            return new SalesPostResponseDto(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getUsername(), entity.getContent());
        } else {
            throw new IllegalArgumentException("해당 ID의 게시글을 찾을 수 없습니다: " + id);
        }
    }

    // 판매 게시글 삭제
    public void deletePost(Long id) {
        if (salesPostRepository.existsById(id)) {
            salesPostRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("해당 ID의 게시글을 찾을 수 없습니다: " + id);
        }
    }
}