package com.ua.fishingforum.user.comments.usecase;

import com.ua.fishingforum.user.comments.web.dto.CommentRequest;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;

public interface CreateCommentUseCase {

    CommentResponse create(CommentRequest commentRequest);
}
