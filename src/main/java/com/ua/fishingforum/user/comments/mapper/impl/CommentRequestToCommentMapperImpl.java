package com.ua.fishingforum.user.comments.mapper.impl;

import com.ua.fishingforum.user.comments.mapper.CommentRequestToCommentMapper;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.web.dto.CommentRequest;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.service.PostService;
import com.ua.fishingforum.user.profile.api.service.CurrentUserProfileApiService;
import com.ua.fishingforum.user.profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentRequestToCommentMapperImpl implements CommentRequestToCommentMapper {
    private final PostService postService;
    private final CurrentUserProfileApiService currentUserProfileApiService;

    @Override
    public Comment map(CommentRequest source) {
        UserProfile currentUserProfile = currentUserProfileApiService.currentUserProfile();
        Post post = postService.findPostById(source.postId());
        Comment comment = new Comment();
        comment.setComment(source.message());
        comment.setPost(post);
        comment.setUserProfile(currentUserProfile);
        return comment;
    }
}
