package com.ua.fishingforum.user.comments.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;

public interface CommentToCommentResponseMapper extends Mapper<CommentResponse, Comment> {
}
