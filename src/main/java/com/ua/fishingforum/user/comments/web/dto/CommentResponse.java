package com.ua.fishingforum.user.comments.web.dto;


import com.ua.fishingforum.user.posts.image.model.Photo;

public record CommentResponse(
        String message,
        String nickname,
        Photo imageUrl,
        Long countLikes
) {
}
