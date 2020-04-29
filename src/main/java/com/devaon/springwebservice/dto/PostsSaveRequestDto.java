package com.devaon.springwebservice.dto;

import com.devaon.springwebservice.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by qwone4@gmail.com on 2020-04-29
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */
@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
