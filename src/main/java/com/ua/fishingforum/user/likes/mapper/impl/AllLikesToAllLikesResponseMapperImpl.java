package com.ua.fishingforum.user.likes.mapper.impl;

import com.ua.fishingforum.user.likes.mapper.AllLikesToAllLikesResponseMapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllLikesToAllLikesResponseMapperImpl implements AllLikesToAllLikesResponseMapper {
    @Override
    public AllLikesResponse map(List<Like> source) {
        return new AllLikesResponse(
                source.stream()
                        .map(like ->
                                new LikeResponse(
                                        like.getUserProfile().getNickname(),
                                        like.getUserProfile().getImageLink()
                                )
                        )
                        .toList()
        );
    }
}
