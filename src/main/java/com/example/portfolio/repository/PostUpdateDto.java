package com.example.portfolio.repository;

import lombok.Data;

@Data
public class PostUpdateDto {
    private String title;
    private String content;
    private String author;

    public PostUpdateDto(){
    }

    public PostUpdateDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
