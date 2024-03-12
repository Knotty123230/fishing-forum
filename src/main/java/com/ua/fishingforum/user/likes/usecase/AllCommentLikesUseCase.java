package com.ua.fishingforum.user.likes.usecase;

import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;

public interface AllCommentLikesUseCase {
    AllLikesResponse findAllCommentsLikes(Long commentId);
}
