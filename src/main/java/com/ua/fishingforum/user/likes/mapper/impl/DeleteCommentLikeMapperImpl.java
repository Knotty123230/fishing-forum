package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.likes.mapper.DeleteCommentLikeMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCommentLikeMapperImpl implements DeleteCommentLikeMapper {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final LikeService likeService;

    @Override
    public Like map(Long source) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        return likeService.findLikeByUserProfileIdAndCommentId(userProfile.getId(), source).orElseThrow(
                () -> new CustomException("коментарій з id %s не знайдено або це не ваший лайк".formatted(source))
        );
    }
}
