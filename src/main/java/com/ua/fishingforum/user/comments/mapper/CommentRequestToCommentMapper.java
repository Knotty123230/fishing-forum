package com.ua.fishingforum.user.comments.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.web.dto.CommentRequest;

public interface CommentRequestToCommentMapper extends Mapper<Comment, CommentRequest> {
}
