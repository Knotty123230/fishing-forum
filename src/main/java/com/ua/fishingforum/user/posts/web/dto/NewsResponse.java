package com.ua.fishingforum.user.posts.web.dto;

import com.ua.fishingforum.user.posts.model.Post;

import java.util.Collection;

public record NewsResponse(boolean isFirstPage, boolean isLastPage, int countOfPages, Collection<Post> posts) {
}
