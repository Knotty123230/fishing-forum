package com.ua.fishingforum.user.posts.web.dto;

import java.time.Instant;

public record PostResponse(String name, String description, String imageUrl, Instant createdAt) {

}
