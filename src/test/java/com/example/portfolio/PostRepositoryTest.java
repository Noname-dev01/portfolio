package com.example.portfolio;

import com.example.portfolio.domain.posts.Posts;
import com.example.portfolio.repository.PostSearchCond;
import com.example.portfolio.repository.PostUpdateDto;
import com.example.portfolio.repository.posts.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    void save() {
        //given
        Posts posts = new Posts("test", "test", "admin");
        //when
        Posts savedPost = postRepository.save(posts);
        //then
        Posts findPost = postRepository.findById(posts.getId()).get();
        assertThat(findPost).isEqualTo(savedPost);
    }
    @Test
    void updatePost() {
        //given
        Posts posts = new Posts("test","test","admin");
        Posts savedPost = postRepository.save(posts);
        Long postId = savedPost.getId();
        //when
        PostUpdateDto updateParam = new PostUpdateDto("test1", "test1", "admin1");
        postRepository.update(postId,updateParam);
        //then
        Posts findPost = postRepository.findById(postId).get();
        assertThat(findPost.getTitle()).isEqualTo(updateParam.getTitle());
        assertThat(findPost.getContent()).isEqualTo(updateParam.getContent());
        assertThat(findPost.getAuthor()).isEqualTo(updateParam.getAuthor());
    }

    @Test
    void findItems() {
        Posts post1 = new Posts("testA-1", "testA-1", "admin-1");
        Posts post2 = new Posts("testA-2", "testA-2", "admin-2");
        Posts post3 = new Posts("testB-3", "testA-3", "admin-3");

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        test(null,null,post1,post2,post3);
        test("",null,post1,post2,post3);

        test("testA",null,post1,post2);
        test("test",null,post1,post2,post3);
        test("testB",null,post3);

        test(null,"admin",post1,post2,post3);
        test(null,"min",post1,post2,post3);
        test(null,"-3",post3);

        test("testA-1","admin-1",post1);
    }

    void test(String titleCheck, String authorCheck,Posts ... posts) {
        List<Posts> result = postRepository.findAll(new PostSearchCond(titleCheck, authorCheck));
        assertThat(result).containsExactly(posts);
    }

}
