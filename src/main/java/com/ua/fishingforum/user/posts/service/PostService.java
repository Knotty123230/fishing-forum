package com.ua.fishingforum.user.posts.service;

import com.ua.fishingforum.user.posts.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PostService {
    Post create(Post post);

    Post findPostById(Long id);

    Page<Post> findAllPostsForCurrentUser(Long id, PageRequest pageRequest);

    Page<Post> findAllNews(Long id, PageRequest pageRequest);
}
