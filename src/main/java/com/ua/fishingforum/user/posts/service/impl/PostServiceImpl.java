package com.ua.fishingforum.user.posts.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.repository.PostRepository;
import com.ua.fishingforum.user.posts.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new CustomException("пост з айді %s не знайдено".formatted(id)));
    }

    @Override
    public Page<Post> findAllPostsForCurrentUser(Long id, PageRequest pageRequest) {
        return postRepository.findAllPostsByUserProfileId(id, pageRequest);
    }

    @Override
    public Page<Post> findAllNews(Long id, PageRequest pageRequest) {
        return postRepository.findNews(id, pageRequest);
    }
}
