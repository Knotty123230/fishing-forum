package com.ua.fishingforum.user.likes.usecase.impl;

import com.ua.fishingforum.user.likes.mapper.CommentLikeMapper;
import com.ua.fishingforum.user.likes.mapper.LikeToCommentLikeResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.likes.usecase.AddCommentLikeUseCase;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddCommentLikeUseCaseFacade implements AddCommentLikeUseCase {
    private final CommentLikeMapper commentLikeMapper;
    private final LikeService likeService;
    private final LikeToCommentLikeResponseMapper likeToCommentLikeResponseMapper;

    @Override
    public LikeResponse addCommentLike(Long commentId) {
        Like like = commentLikeMapper.map(commentId);
        Like createdLike = likeService.addLike(like);
        return likeToCommentLikeResponseMapper.map(createdLike);
    }
}
