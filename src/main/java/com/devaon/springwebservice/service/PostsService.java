package com.devaon.springwebservice.service;

import com.devaon.springwebservice.domain.posts.PostsRepository;
import com.devaon.springwebservice.dto.PostsMainResponseDto;
import com.devaon.springwebservice.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qwone4@gmail.com on 2020-04-30
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */

@Service
@AllArgsConstructor
public class PostsService {

    private PostsRepository postsRepository;


    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAlDesc(){
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

}
