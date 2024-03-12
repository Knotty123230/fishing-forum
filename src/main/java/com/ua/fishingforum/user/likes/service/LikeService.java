package com.ua.fishingforum.user.likes.service;

import com.ua.fishingforum.user.likes.model.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    Like addLike(Like like);

    List<Like> findAllPostLikes(Long postId);

    List<Like> findAllCommentLikes(Long commentId);

    Optional<Like> findLikeByPostIdAndUserProfileId(Long postId, Long id);

    void deleteLike(Like like);

    Optional<Like> findLikeByUserProfileIdAndCommentId(Long id, Long source);
}