package com.ua.fishingforum.user.comments.usecase.impl;

import com.ua.fishingforum.user.comments.mapper.CommentRequestToCommentMapper;
import com.ua.fishingforum.user.comments.mapper.CommentToCommentResponseMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.service.CommentService;
import com.ua.fishingforum.user.comments.usecase.CreateCommentUseCase;
import com.ua.fishingforum.user.comments.web.dto.CommentRequest;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCommentUseCaseFacade implements CreateCommentUseCase {
    private final CommentService commentService;
    private final CommentRequestToCommentMapper commentRequestToCommentMapper;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    @Override
    public CommentResponse create(CommentRequest commentRequest) {
        Comment comment = commentRequestToCommentMapper.map(commentRequest);
        Comment createdComment = commentService.create(comment);
        return commentToCommentResponseMapper.map(createdComment);
    }
}
