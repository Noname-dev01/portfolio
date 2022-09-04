package com.example.portfolio.repository.posts;

import com.example.portfolio.domain.posts.Posts;
import com.example.portfolio.repository.PostRepositoryInterface;
import com.example.portfolio.repository.PostSearchCond;
import com.example.portfolio.repository.PostUpdateDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.portfolio.domain.posts.QPosts.posts;

@Repository
@Transactional
public class PostRepository implements PostRepositoryInterface {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public PostRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Posts save(Posts posts) {
         em.persist(posts);
         return posts;
    }

    @Override
    public void update(Long postId, PostUpdateDto updateParam) {
        Posts findPost = em.find(Posts.class, postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
        findPost.setAuthor(updateParam.getAuthor());
    }

    @Override
    public Optional<Posts> findById(Long id) {
        Posts posts = em.find(Posts.class, id);
        return Optional.ofNullable(posts);
    }

    @Override
    public List<Posts> findAll(PostSearchCond cond) {

        String titleCheck = cond.getTitleCheck();
        String authorCheck = cond.getAuthorCheck();

        return query
                .select(posts)
                .from(posts)
                .where(likeTitleCheck(titleCheck),likeAuthorCheck(authorCheck))
                .fetch();

    }

    private BooleanExpression likeTitleCheck(String titleCheck) {
        if (StringUtils.hasText(titleCheck)){
            return posts.title.like("%"+titleCheck+"%");
        }
        return null;
    }

    private BooleanExpression likeAuthorCheck(String authorCheck) {
        if (StringUtils.hasText(authorCheck)){
            return posts.author.like("%"+authorCheck+"%");
        }
        return null;
    }
}
