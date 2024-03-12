package com.ua.fishingforum.user.likes.usecase.impl;

import com.ua.fishingforum.user.likes.mapper.LikeMapper;
import com.ua.fishingforum.user.likes.mapper.LikeToLikeResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.likes.usecase.AddLikeUseCase;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddLikeUseCaseFacade implements AddLikeUseCase {

    private final LikeMapper likeMapper;
    private final LikeService likeService;
    private final LikeToLikeResponseMapper likeToLikeResponseMapper;

    @Override
    public LikeResponse addLike(Long postId) {
        Like like = likeMapper.map(postId);
        Like createdLike = likeService.addLike(like);
        return likeToLikeResponseMapper.map(createdLike);
    }
}
