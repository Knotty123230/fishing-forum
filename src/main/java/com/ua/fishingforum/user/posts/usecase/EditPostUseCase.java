package com.ua.fishingforum.user.posts.usecase;

import com.ua.fishingforum.user.posts.web.dto.PostRequest;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;

public interface EditPostUseCase {
    PostResponse editPost(Long postId, PostRequest postRequest);
}
