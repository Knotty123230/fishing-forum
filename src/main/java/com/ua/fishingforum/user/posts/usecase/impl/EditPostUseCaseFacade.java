package com.ua.fishingforum.user.posts.usecase.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.posts.mapper.PostToPostResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.usecase.EditPostUseCase;
import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditPostUseCaseFacade implements EditPostUseCase {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final PostService postService;
    private final PostToPostResponseMapper postToPostResponseMapper;

    @Override
    public PostResponse editPost(Long postId, PostRequest postRequest) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Post post = getPost(postId, postRequest, userProfile);
        Post createdPost = postService.create(post);
        return postToPostResponseMapper.map(createdPost);
    }

    private Post getPost(Long postId, PostRequest postRequest, UserProfile userProfile) {
        Post post = postService.findPostByUserProfileAndId(userProfile, postId).orElseThrow(() -> new CustomException("ви не можете коригувати цей пост"));
        post.setDescription(postRequest.description());
        post.setName(postRequest.name());
        post.setImageUrl(postRequest.imageUrl());
        return post;
    }
}
