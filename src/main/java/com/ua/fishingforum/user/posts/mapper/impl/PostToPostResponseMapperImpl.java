package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.mapper.PostToPostResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import org.springframework.stereotype.Component;

@Component
public class PostToPostResponseMapperImpl implements PostToPostResponseMapper {
    @Override
    public PostResponse map(Post source) {
        return new PostResponse(source.getName(),
                source.getDescription(),
                source.getImageUrl(),
                source.getCreatedTimestamp());
    }
}
