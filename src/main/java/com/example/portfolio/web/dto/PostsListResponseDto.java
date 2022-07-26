package com.example.portfolio.web.dto;

import com.example.portfolio.domain.posts.Posts;
import lombok.Getter;


@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;


    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();

    }
}
