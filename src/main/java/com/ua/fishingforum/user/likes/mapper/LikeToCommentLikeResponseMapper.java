package com.ua.fishingforum.user.likes.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.web.dto.LikeResponse;

public interface LikeToCommentLikeResponseMapper extends Mapper<LikeResponse, Like> {
}
