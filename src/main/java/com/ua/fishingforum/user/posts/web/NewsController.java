package com.ua.fishingforum.user.posts.web;

import com.ua.fishingforum.user.posts.usecase.FindAllPostsUseCase;
import com.ua.fishingforum.user.posts.usecase.NewsUseCase;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsUseCase newsUseCase;
    private final FindAllPostsUseCase findAllPostsUseCase;

    @GetMapping("/date")
    public NewsResponse news(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return newsUseCase.findNews(page, limit);
    }

    @GetMapping("/likes")
    public AllPostsResponse findAllPostsByLikes(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return findAllPostsUseCase.findByLikes(page, limit);
    }

}
