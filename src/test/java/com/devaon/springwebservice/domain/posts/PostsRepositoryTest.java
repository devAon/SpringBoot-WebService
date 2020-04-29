package com.devaon.springwebservice.domain.posts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by qwone4@gmail.com on 2020-04-29
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */

@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @Test
    void getPostsTest(){
        //given
        postsRepository.save(Posts.builder()
        .title("테스트 제목")
        .content("테스트 본문")
        .author("테스터")
        .build());


        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo("테스트 제목");
        assertThat(posts.getContent()).isEqualTo("테스트 본문");
        assertThat(posts.getAuthor()).isEqualTo("테스터");
    }

    @Test
    void BaseTimeEntityTest(){
        //given
        LocalDateTime now = LocalDateTime.now();

        postsRepository.save(Posts.builder()
                .title("테스트 제목")
                .content("테스트 본문")
                .author("테스터")
                .build());


        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));

    }

}