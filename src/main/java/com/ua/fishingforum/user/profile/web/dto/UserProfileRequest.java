package com.ua.fishingforum.user.profile.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileRequest(
        @NotBlank String nickname,
        @NotBlank String imageLink
) {
}
