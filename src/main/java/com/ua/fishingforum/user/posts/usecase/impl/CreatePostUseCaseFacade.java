package com.ua.fishingforum.user.posts.usecase.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.mapper.PostRequestToPostMapper;
import com.ua.fishingforum.user.posts.mapper.PostToPostResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.usecase.CreatePostUseCase;
import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePostUseCaseFacade implements CreatePostUseCase {
    private final PostRequestToPostMapper postRequestToPostMapper;
    private final PostToPostResponseMapper postToPostResponseMapper;
    private final ImageStorageService imageStorageService;
    private final PostService postService;

    @Override
    public PostResponse create(PostRequest postRequest) {
        Post post = postRequestToPostMapper.map(postRequest);
        Post createdPost = postService.create(post);
        return postToPostResponseMapper.map(createdPost);
    }
}