package com.ua.fishingforum.user.comments.usecase;

import com.ua.fishingforum.user.comments.web.dto.AllCommentsResponse;

public interface AllCommentsUseCase {

    AllCommentsResponse findAllComments(Long postId);
}
