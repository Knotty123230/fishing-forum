package com.ua.fishingforum.user.posts.web.dto;

import com.ua.fishingforum.user.posts.model.Photo;

import java.time.Instant;
import java.util.List;

public record PostResponse(String name, String description, List<Photo> images, Instant createdAt) {

}
