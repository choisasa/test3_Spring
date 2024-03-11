package com.sparta.test.dto;

import lombok.Getter;

@Getter
public class SalesPostRequestDto {
    private Long id;
    private String title;
    private String content;
    private int price;
    private String username;
}
