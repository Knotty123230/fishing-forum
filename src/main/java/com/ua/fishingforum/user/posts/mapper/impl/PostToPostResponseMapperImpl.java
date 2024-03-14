package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.mapper.PostToPostResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostToPostResponseMapperImpl implements PostToPostResponseMapper {
    private final PostService postService;

    @Override
    public PostResponse map(Post post) {
        Post createdPost = postService.create(post);
        return new PostResponse(createdPost.getName(),
                createdPost.getDescription(),
                createdPost.getImageUrl(),
                createdPost.getCreatedTimestamp());
    }
}
