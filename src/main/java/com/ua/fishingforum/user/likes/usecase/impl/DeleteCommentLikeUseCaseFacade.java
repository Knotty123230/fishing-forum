package com.ua.fishingforum.user.likes.usecase.impl;

import com.ua.fishingforum.user.likes.mapper.DeleteCommentLikeMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.likes.usecase.DeleteCommentLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCommentLikeUseCaseFacade implements DeleteCommentLikeUseCase {
    private final DeleteCommentLikeMapper deleteCommentLikeMapper;
    private final LikeService likeService;

    @Override
    public void deleteCommentLike(Long commentId) {
        Like like = deleteCommentLikeMapper.map(commentId);
        likeService.deleteLike(like);
    }
}
