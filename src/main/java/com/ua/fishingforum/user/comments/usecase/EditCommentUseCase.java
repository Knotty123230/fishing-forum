package com.ua.fishingforum.user.comments.usecase;

import com.ua.fishingforum.user.comments.web.dto.CommentResponse;

public interface EditCommentUseCase {
    CommentResponse editComment(Long commentId, String message);
}
