package com.ua.fishingforum.user.comments.mapper.impl;

import com.ua.fishingforum.user.comments.mapper.CommentToCommentResponseMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentResponseMapperImpl implements CommentToCommentResponseMapper {
    @Override
    public CommentResponse map(Comment source) {
        return new CommentResponse(source.getMessage(),
                source.getUserProfile().getNickname(),
                source.getUserProfile().getImageLink(),
                (long) source.getLikes().size());
    }
}
