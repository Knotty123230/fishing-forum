package com.ua.fishingforum.user.posts.web.dto;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(
        @NotBlank String name,
        String description,
        String imageUrl
) {
}
