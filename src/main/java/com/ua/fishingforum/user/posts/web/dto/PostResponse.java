package com.ua.fishingforum.user.posts.web.dto;

import java.time.Instant;
import java.util.List;

public record PostResponse(
        String name,
        String description,
        List<PhotoResponse> images,
        Instant createdAt
) {

}
