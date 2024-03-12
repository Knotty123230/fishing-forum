package com.ua.fishingforum.user.posts.web.dto;


import com.ua.fishingforum.user.posts.model.Post;

import java.util.Collection;

public record AllPostsResponse(boolean isFirstPage, boolean isLastPage, int countOfPosts, Collection<Post> posts) {
}
