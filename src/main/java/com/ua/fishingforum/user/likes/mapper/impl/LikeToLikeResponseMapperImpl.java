package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.user.likes.mapper.LikeToLikeResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import org.springframework.stereotype.Component;

@Component
public class LikeToLikeResponseMapperImpl implements LikeToLikeResponseMapper {
    @Override
    public LikeResponse map(Like source) {
        return new LikeResponse(
                source.getUserProfile().getNickname(),
                source.getUserProfile().getImageLink()
        );
    }
}
