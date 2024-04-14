package com.ua.fishingforum.user.posts.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.repository.PostRepository;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
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
    @CacheEvict(value = {"posts", "posts-news", "posts-for-curr-user"}, allEntries = true)
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new CustomException("пост з айді %s не знайдено".formatted(id)));
    }

    @Override
    @Cacheable(value = "posts-for-curr-user", key = "#userProfileId")
    public Page<Post> findAllPostsForCurrentUser(Long userProfileId, PageRequest pageRequest) {
        return postRepository.findAllPostsByUserProfileId(userProfileId, pageRequest);
    }

    @Override
    @Cacheable(value = "posts-news", key = "#userProfileId")
    public Page<Post> findAllNews(Long userProfileId, PageRequest pageRequest) {
        return postRepository.findNews(userProfileId, pageRequest);
    }

    @Override
    @Cacheable(value = "posts", key = "#userProfileId")
    public Page<Post> findAll(Long userProfileId, PageRequest pageRequest) {
        return this.postRepository.findAll(userProfileId, pageRequest);
    }

    @Override
    public Optional<Post> findPostByUserProfileAndId(UserProfile userProfile, Long postId) {
        return postRepository.findByUserProfileAndId(userProfile, postId);
    }
}
