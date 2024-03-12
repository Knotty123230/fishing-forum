package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.mapper.PagePostsToAllPostsResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PagePostsToAllPostsResponseMapperImpl implements PagePostsToAllPostsResponseMapper {
    @Override
    public AllPostsResponse map(Page<Post> source) {
        return new AllPostsResponse(
                source.isFirst(),
                source.isLast(),
                source.getTotalPages(),
                source.stream().toList()
        );
    }
}
