package com.ua.fishingforum.user.posts.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;

public interface PostToPostResponseMapper extends Mapper<PostResponse, Post> {
}
