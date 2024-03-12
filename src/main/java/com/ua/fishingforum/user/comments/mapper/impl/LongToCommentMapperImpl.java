package com.ua.fishingforum.user.comments.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.comments.mapper.LongToCommentMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.service.CommentService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LongToCommentMapperImpl implements LongToCommentMapper {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final CommentService commentService;

    @Override
    public Comment map(Long source) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        return commentService.findCommentByUserProfileIdAndCommentId(userProfile.getId(), source).orElseThrow(() ->
                new CustomException("Comment with id %s not found".formatted(source)));
    }
}
