package com.ua.fishingforum.user.comments.usecase.impl;

import com.ua.fishingforum.user.comments.mapper.CommentToCommentResponseMapper;
import com.ua.fishingforum.user.comments.mapper.LongToCommentMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.service.CommentService;
import com.ua.fishingforum.user.comments.usecase.EditCommentUseCase;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditCommentUseCaseFacade implements EditCommentUseCase {
    private final LongToCommentMapper commentMapper;
    private final CommentService commentService;
    private final CommentToCommentResponseMapper commentToCommentResponseMapper;

    @Override
    public CommentResponse editComment(Long commentId, String message) {
        Comment comment = commentMapper.map(commentId);
        comment.setMessage(message);
        Comment createdComment = commentService.create(comment);
        return commentToCommentResponseMapper.map(createdComment);
    }
}
