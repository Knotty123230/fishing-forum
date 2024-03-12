package com.ua.fishingforum.user.posts.usecase;

import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;

public interface CreatePostUseCase {
    PostResponse create(PostRequest postRequest);
}
