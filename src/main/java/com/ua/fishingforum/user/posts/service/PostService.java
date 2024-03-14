package com.ua.fishingforum.user.posts.service;

import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.profile.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface PostService {
    Post create(Post post);

    Post findPostById(Long id);

    Page<Post> findAllPostsForCurrentUser(Long id, PageRequest pageRequest);

    Page<Post> findAllNews(Long id, PageRequest pageRequest);

    Page<Post> findAll(Long userProfileId, PageRequest pageRequest);

    Optional<Post> findPostByUserProfileAndId(UserProfile userProfile, Long postId);
}
