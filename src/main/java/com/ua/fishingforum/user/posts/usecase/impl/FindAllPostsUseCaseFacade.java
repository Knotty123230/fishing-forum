package com.ua.fishingforum.user.posts.usecase.impl;

import com.ua.fishingforum.user.posts.mapper.PagePostsToAllPostsResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.posts.usecase.FindAllPostsUseCase;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllPostsUseCaseFacade implements FindAllPostsUseCase {
    private final PostService postService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final PagePostsToAllPostsResponseMapper pagePostsToAllPostsResponseMapper;


    @Override
    public AllPostsResponse find(int page, int limit) {
        Sort createdTimestamp = Sort.by(Sort.Direction.DESC, "createdTimestamp");
        PageRequest pageRequest = PageRequest.of(page, limit, createdTimestamp);
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Page<Post> allPostsForCurrentUser = postService.findAllPostsForCurrentUser(userProfile.getId(), pageRequest);
        return pagePostsToAllPostsResponseMapper.map(allPostsForCurrentUser);
    }

    @Override
    public AllPostsResponse findByLikes(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Page<Post> posts = postService.findAll(userProfile.getId(), pageRequest);
        List<Post> postList = posts.stream().sorted(Comparator.comparing(
                        post -> post.getLikes().size(),
                        Comparator.reverseOrder())
                )
                .toList();
        Page<Post> postPage = new PageImpl<>(postList);
        return pagePostsToAllPostsResponseMapper.map(postPage);
    }
}
