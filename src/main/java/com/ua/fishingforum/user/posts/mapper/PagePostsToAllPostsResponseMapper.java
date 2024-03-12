package com.ua.fishingforum.user.posts.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import org.springframework.data.domain.Page;

public interface PagePostsToAllPostsResponseMapper extends Mapper<AllPostsResponse, Page<Post>> {
}
