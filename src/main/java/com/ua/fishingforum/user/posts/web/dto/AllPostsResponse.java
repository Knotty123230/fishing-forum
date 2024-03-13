package com.ua.fishingforum.user.posts.web.dto;


import java.util.List;

public record AllPostsResponse(boolean isFirstPage, boolean isLastPage, int countOfPosts,
                               List<PostResponse> postResponse) {
}
