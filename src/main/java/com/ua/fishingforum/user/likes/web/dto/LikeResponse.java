package com.ua.fishingforum.user.likes.web.dto;

import com.ua.fishingforum.user.posts.image.model.Photo;

public record LikeResponse(String nickname, Photo imageLink) {
}
