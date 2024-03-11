package com.sparta.test.dto;

import lombok.Getter;

@Getter
public class SalesPostResponseDto {
    private Long id;
    private String title;
    private int price;
    private String username;
    private String content;

    public SalesPostResponseDto(Long id, String title, int price, String username, String content) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.username = username;
        this.content = content;
    }
}
