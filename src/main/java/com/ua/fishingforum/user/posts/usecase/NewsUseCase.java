package com.ua.fishingforum.user.posts.usecase;

import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;

@Validated
public interface NewsUseCase {
    NewsResponse findNews(
            @Valid @Min(0) @Max(100) int page,
            @Min(50) @Max(180) int limit
    );
}
