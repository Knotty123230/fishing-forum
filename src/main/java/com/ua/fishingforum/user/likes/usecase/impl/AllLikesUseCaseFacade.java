package com.ua.fishingforum.user.likes.usecase.impl;

import com.ua.fishingforum.user.likes.mapper.LikesToAllLikeResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.likes.usecase.AllLikesUseCase;
import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AllLikesUseCaseFacade implements AllLikesUseCase {
    private final LikeService likeService;
    private final LikesToAllLikeResponseMapper allLikeResponseMapper;

    @Override
    public AllLikesResponse findAll(Long postId) {
        List<Like> likes = likeService.findAllPostLikes(postId);
        return allLikeResponseMapper.map(likes);
    }
}
