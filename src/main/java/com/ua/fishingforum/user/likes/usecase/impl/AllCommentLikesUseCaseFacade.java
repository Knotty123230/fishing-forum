package com.ua.fishingforum.user.likes.usecase.impl;

import com.ua.fishingforum.user.likes.mapper.AllLikesToAllLikesResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.service.LikeService;
import com.ua.fishingforum.user.likes.usecase.AllCommentLikesUseCase;
import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AllCommentLikesUseCaseFacade implements AllCommentLikesUseCase {
    private final LikeService likeService;
    private final AllLikesToAllLikesResponseMapper allLikesToAllLikesResponseMapper;

    @Override
    public AllLikesResponse findAllCommentsLikes(Long commentId) {
        List<Like> allCommentLikes = likeService.findAllCommentLikes(commentId);
        return allLikesToAllLikesResponseMapper.map(allCommentLikes);
    }
}
