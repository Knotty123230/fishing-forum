package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.mapper.PagePostsToNewsResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PagePostsToNewsResponseMapperImpl implements PagePostsToNewsResponseMapper {
    @Override
    public NewsResponse map(Page<Post> source) {
        return new NewsResponse(source.isFirst(),
                source.isLast(),
                source.getTotalPages(),
                source.stream().toList());
    }
}
