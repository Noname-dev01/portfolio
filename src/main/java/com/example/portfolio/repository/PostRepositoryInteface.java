package com.example.portfolio.repository;

import com.example.portfolio.domain.posts.Posts;

import java.util.List;
import java.util.Optional;

public interface PostRepositoryInteface {

    Posts save(Posts posts);

    void update(Long postId, PostUpdateDto updateParam);

    Optional<Posts> findById(Long id);

    List<Posts> findAll(PostSearchCond cond);

}
