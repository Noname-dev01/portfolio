package com.example.portfolio.service;

import com.example.portfolio.domain.posts.Posts;
import com.example.portfolio.repository.posts.JpaPostRepository;
import com.example.portfolio.repository.PostSearchCond;
import com.example.portfolio.repository.PostUpdateDto;
import com.example.portfolio.repository.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public Posts save(Posts posts) {return postRepository.save(posts);}

    public void update(Long postId, PostUpdateDto updateParam){
        Posts findPost = postRepository.findById(postId).orElseThrow();
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
        findPost.setAuthor(updateParam.getAuthor());
    }
    public Optional<Posts> findById(Long id) {return postRepository.findById(id);}

    public List<Posts> findPosts(PostSearchCond cond){
        return postRepository.findAll(cond);
    }

}
