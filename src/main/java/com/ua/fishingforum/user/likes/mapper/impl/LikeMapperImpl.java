package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.likes.mapper.LikeMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LikeMapperImpl implements LikeMapper {
    private final PostService postService;
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final LikeService likeService;

    @Override
    public Like map(Long source) {
        Post post = postService.findPostById(source);
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Optional<Like> likeByPostIdAndUserProfileId =
                likeService.findLikeByPostIdAndUserProfileId(source, userProfile.getId());
        if (likeByPostIdAndUserProfileId.isPresent()) {
            throw new CustomException("лайк для цього поста вже поставлено");
        }
        Like like = new Like();
        like.setUserProfile(userProfile);
        like.setPost(post);
        return like;
    }
}
