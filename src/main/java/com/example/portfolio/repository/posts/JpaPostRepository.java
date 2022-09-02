package com.example.portfolio.repository.posts;

import com.example.portfolio.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<Posts,Long> {
}
