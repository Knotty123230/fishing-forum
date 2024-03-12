package com.ua.fishingforum.user.comments.web.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CommentRequest(
        @Length(min = 3, max = 150) String message,
        @NotNull Long postId
) {

}
