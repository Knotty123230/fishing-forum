package com.ua.fishingforum.user.comments.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.web.dto.AllCommentsResponse;

import java.util.List;

public interface ListToAllCommentsResponseMapper extends Mapper<AllCommentsResponse, List<Comment>> {
}
