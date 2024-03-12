package com.ua.fishingforum.user.likes.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.web.dto.AllLikesResponse;

import java.util.List;

public interface AllLikesToAllLikesResponseMapper extends Mapper<AllLikesResponse, List<Like>> {
}
