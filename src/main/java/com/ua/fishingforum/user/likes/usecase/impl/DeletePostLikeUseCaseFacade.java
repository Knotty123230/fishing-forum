package com.ua.fishingforum.user.likes.usecase.impl;

import com.ua.fishingforum.user.likes.mapper.DeleteLikeMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.likes.usecase.DeletePostLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeletePostLikeUseCaseFacade implements DeletePostLikeUseCase {

    private final DeleteLikeMapper deleteLikeMapper;
    private final LikeService likeService;

    @Override
    public void delete(Long postId) {
        Like like = deleteLikeMapper.map(postId);
        likeService.deleteLike(like);
    }
}
