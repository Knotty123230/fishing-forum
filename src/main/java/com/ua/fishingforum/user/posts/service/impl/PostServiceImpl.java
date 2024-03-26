package com.ua.fishingforum.user.posts.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.repository.PostRepository;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"posts", "posts-news"}, key = "#post.userProfile.id")
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new CustomException("пост з айді %s не знайдено".formatted(id)));
    }

    @Override
    @Cacheable(value = "posts", key = "#id")
    public Page<Post> findAllPostsForCurrentUser(Long id, PageRequest pageRequest) {
        return postRepository.findAllPostsByUserProfileId(id, pageRequest);
    }

    @Override
    @Cacheable(value = "posts-news", key = "#id")
    public Page<Post> findAllNews(Long id, PageRequest pageRequest) {
        return postRepository.findNews(id, pageRequest);
    }

    @Override
    @CacheEvict(value = "posts", key = "#id")
    @Cacheable(value = "posts", key = "#id")
    public Page<Post> findAll(Long id, PageRequest pageRequest) {
        return this.postRepository.findAll(id, pageRequest);
    }

    @Override
    public Optional<Post> findPostByUserProfileAndId(UserProfile userProfile, Long postId) {
        return postRepository.findByUserProfileAndId(userProfile, postId);
    }
}
