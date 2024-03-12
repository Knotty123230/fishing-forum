package com.ua.fishingforum.user.posts.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import org.springframework.data.domain.Page;

public interface PagePostsToNewsResponseMapper extends Mapper<NewsResponse, Page<Post>> {
}
