package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.user.likes.mapper.LikeToCommentLikeResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import org.springframework.stereotype.Component;

@Component
public class LikeToCommentLikeResponseMapperImpl implements LikeToCommentLikeResponseMapper {
    @Override
    public LikeResponse map(Like source) {
        return new LikeResponse(
                source.getUserProfile().getNickname(),
                source.getUserProfile().getImageLink());
    }
}
