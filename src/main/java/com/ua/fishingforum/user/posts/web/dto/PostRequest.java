package com.ua.fishingforum.user.posts.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRequest(
        @NotBlank @NotNull String name,
        @NotNull String description,
        @NotNull String imageUrl
) {
}
