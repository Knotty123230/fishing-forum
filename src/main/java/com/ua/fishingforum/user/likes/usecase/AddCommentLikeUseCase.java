package com.ua.fishingforum.user.likes.usecase;

import com.ua.fishingforum.user.likes.web.dto.LikeResponse;

public interface AddCommentLikeUseCase {
    LikeResponse addCommentLike(Long commentId);
}
