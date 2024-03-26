package com.ua.fishingforum.user.posts.web.dto;

import java.time.Instant;

public record PhotoResponse(String photoUrl, Instant createdAt) {
}
