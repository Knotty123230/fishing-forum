package com.ua.fishingforum.user.comments.mapper.impl;

import com.ua.fishingforum.user.comments.mapper.ListToAllCommentsResponseMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.web.dto.AllCommentsResponse;
import com.ua.fishingforum.user.comments.web.dto.CommentResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListToAllCommentsResponseMapperImpl implements ListToAllCommentsResponseMapper {
    @Override
    public AllCommentsResponse map(List<Comment> source) {
        return new AllCommentsResponse(
                source.stream()
                        .map(comment -> new CommentResponse(
                                comment.getComment(),
                                comment.getUserProfile().getNickname(),
                                comment.getUserProfile().getImageLink(),
                                (long) comment.getLikes().size())
                        )
                        .toList()
        );
    }
}
