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

import static com.ua.fishingforum.common.constants.MappingConstants.NEWS_BY_DATE_MAPPING;
import static com.ua.fishingforum.common.constants.MappingConstants.NEWS_BY_LIKES_MAPPING;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsController {
    private final NewsUseCase newsUseCase;
    private final FindAllPostsUseCase findAllPostsUseCase;

    @GetMapping(NEWS_BY_DATE_MAPPING)
    public NewsResponse news(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return newsUseCase.findNews(page, limit);
    }

    @GetMapping(NEWS_BY_LIKES_MAPPING)
    public AllPostsResponse findAllPostsByLikes(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return findAllPostsUseCase.findByLikes(page, limit);
    }

}
