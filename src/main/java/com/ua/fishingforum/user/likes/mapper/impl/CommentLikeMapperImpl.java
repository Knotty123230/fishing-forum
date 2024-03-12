package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.service.CommentService;
import com.ua.fishingforum.user.likes.mapper.CommentLikeMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommentLikeMapperImpl implements CommentLikeMapper {
    private final CurrentUserProfileApiService currentUserProfileApiService;
    private final CommentService commentService;
    private final LikeService likeService;

    @Override
    public Like map(Long commentId) {
        UserProfile userProfile = currentUserProfileApiService.currentUserProfile();
        Comment comment = commentService.findCommentById(commentId);
        Optional<Like> optionalLike = likeService.findLikeByUserProfileIdAndCommentId(userProfile.getId(), commentId);
        if (optionalLike.isPresent()) {
            throw new CustomException("Для цього поста лайк вже поставлено");
        }
        Like like = new Like();
        like.setComment(comment);
        like.setUserProfile(userProfile);
        return like;
    }
}
