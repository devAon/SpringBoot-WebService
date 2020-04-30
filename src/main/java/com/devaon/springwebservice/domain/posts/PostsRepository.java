package com.devaon.springwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * Created by qwone4@gmail.com on 2020-04-29
 * Blog : http://aonee.tistory.com
 * Github : http://github.com/devAon
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC ")
    Stream<Posts> findAllDesc();
}
