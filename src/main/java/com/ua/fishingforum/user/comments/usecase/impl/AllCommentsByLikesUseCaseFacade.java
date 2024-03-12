package com.ua.fishingforum.user.comments.usecase.impl;

import com.ua.fishingforum.user.comments.mapper.ListToAllCommentsResponseMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.service.CommentService;
import com.ua.fishingforum.user.comments.usecase.AllCommentsUseCase;
import com.ua.fishingforum.user.comments.web.dto.AllCommentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("byLikes")
@RequiredArgsConstructor
public class AllCommentsByLikesUseCaseFacade implements AllCommentsUseCase {
    private final CommentService commentService;
    private final ListToAllCommentsResponseMapper listToAllCommentsResponseMapper;

    @Override
    public AllCommentsResponse findAllComments(Long postId) {
        List<Comment> comments = commentService.findAllCommentsByPostIdAndOrderByLikes(postId)
                .orElse(commentService.findAllCommentsByPostIdAndOrderByDate(postId));
        return listToAllCommentsResponseMapper.map(comments);
    }
}
