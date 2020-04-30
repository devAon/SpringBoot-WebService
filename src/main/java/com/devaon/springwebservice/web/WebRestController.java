package com.devaon.springwebservice.web;

import com.devaon.springwebservice.dto.PostsSaveRequestDto;
import com.devaon.springwebservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * Created by qwone4@gmail.com on 2020-04-29
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */

@RestController
@AllArgsConstructor
public class WebRestController {

    private PostsService postsService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto){
        return postsService.save(dto);
    }
}
