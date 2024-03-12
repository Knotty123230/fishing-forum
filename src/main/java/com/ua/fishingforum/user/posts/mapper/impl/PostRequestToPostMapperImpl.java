package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.mapper.PostRequestToPostMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRequestToPostMapperImpl implements PostRequestToPostMapper {
    private final CurrentUserProfileApiService currentUserProfileApiService;

    @Override
    public Post map(PostRequest source) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Post post = new Post();
        post.setUserProfile(userProfile);
        post.setName(source.name());
        post.setDescription(source.description());
        post.setImageUrl(source.imageUrl());
        return post;
    }
}
