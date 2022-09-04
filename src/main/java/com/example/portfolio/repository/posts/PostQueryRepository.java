package com.example.portfolio.repository.posts;

import com.example.portfolio.domain.posts.Posts;
import com.example.portfolio.repository.PostSearchCond;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.portfolio.domain.posts.QPosts.posts;

@Repository
public class PostQueryRepository {

    private final JPAQueryFactory query;

    public PostQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Posts> findAll(PostSearchCond cond) {
        String titleCheck = cond.getTitleCheck();
        String authorCheck = cond.getAuthorCheck();

        return query.select(posts)
                .from(posts)
                .where(likeTitle(titleCheck),likeAuthor(authorCheck))
                .fetch();
    }

    private BooleanExpression likeAuthor(String authorCheck) {
        if (StringUtils.hasText(authorCheck)){
            return posts.author.like("%"+authorCheck+"%");
        }
        return null;
    }

    private BooleanExpression likeTitle(String titleCheck) {
        if (StringUtils.hasText(titleCheck)){
            return posts.title.like("%"+titleCheck+"%");
        }
        return null;
    }
}
