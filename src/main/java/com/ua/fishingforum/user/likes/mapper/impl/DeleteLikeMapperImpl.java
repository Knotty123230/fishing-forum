package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.likes.mapper.DeleteLikeMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteLikeMapperImpl implements DeleteLikeMapper {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final LikeService likeService;

    @Override
    public Like map(Long source) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        return likeService.findLikeByPostIdAndUserProfileId(source, userProfile.getId()).orElseThrow(() ->
                new CustomException("лайка для цього поста немає %s".formatted(source)));
    }
}
