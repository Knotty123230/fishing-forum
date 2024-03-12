package com.ua.fishingforum.user.posts.usecase.impl;

import com.ua.fishingforum.user.posts.mapper.PagePostsToNewsResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.usecase.NewsUseCase;
import com.ua.fishingforum.user.posts.web.dto.NewsResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsUseCaseFacade implements NewsUseCase {
    private final PostService postService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final PagePostsToNewsResponseMapper pagePostsToNewsResponseMapper;

    @Override
    public NewsResponse findNews(int page, int limit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTimestamp");
        PageRequest pageRequest = PageRequest.of(page, limit, sort);
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();

        Page<Post> news = postService.findAllNews(userProfile.getId(), pageRequest);

        return pagePostsToNewsResponseMapper.map(news);
    }
}
