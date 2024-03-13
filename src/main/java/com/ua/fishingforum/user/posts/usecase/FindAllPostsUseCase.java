package com.ua.fishingforum.user.posts.usecase;

import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

@Validated
public interface FindAllPostsUseCase {
    AllPostsResponse find(@Min(0) @Max(100) int page, @Min(30) @Max(180) int limit);

    AllPostsResponse findByLikes(int page, int limit);
}
