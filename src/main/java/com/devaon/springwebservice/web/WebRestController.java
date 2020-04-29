package com.devaon.springwebservice.web;

import com.devaon.springwebservice.domain.posts.PostsRepository;
import com.devaon.springwebservice.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * Created by qwone4@gmail.com on 2020-04-29
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class WebRestController {

    private PostsRepository postsRepository;

    @PostMapping
    public void postPosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

}
