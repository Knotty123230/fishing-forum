package com.ua.fishingforum.user.posts.web;

import com.ua.fishingforum.user.posts.usecase.NewsUseCase;
import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class NewsController {
    private final NewsUseCase newsUseCase;

    @GetMapping("/news")
    public NewsResponse news(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return newsUseCase.findNews(page, limit);
    }

}
