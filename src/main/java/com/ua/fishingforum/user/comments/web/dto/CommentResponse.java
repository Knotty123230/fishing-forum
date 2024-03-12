package com.ua.fishingforum.user.comments.web.dto;


public record CommentResponse(
        String message,
        String nickname,
        String imageUrl,
        Long countLikes
) {
}
