package com.devaon.springwebservice.service;

import com.devaon.springwebservice.domain.posts.Posts;
import com.devaon.springwebservice.domain.posts.PostsRepository;
import com.devaon.springwebservice.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by qwone4@gmail.com on 2020-04-30
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */

@SpringBootTest
class PostsServiceTest {
    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void saveTest () {
        //Dto데이터가_posts테이블에_저장된다
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("test1@gmail.com")
                .content("test1 content")
                .title("test1")
                .build();

        //when
        postsService.save(dto);

        //then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
    }
}