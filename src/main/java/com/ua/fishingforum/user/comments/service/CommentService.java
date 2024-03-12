package com.ua.fishingforum.user.comments.service;

import com.ua.fishingforum.user.comments.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment create(Comment comment);

    Comment findCommentById(Long commentId);

    Optional<Comment> findCommentByUserProfileIdAndCommentId(Long id, Long source);

    List<Comment> findAllCommentsByPostIdAndOrderByDate(Long postId);

    Optional<List<Comment>> findAllCommentsByPostIdAndOrderByLikes(Long postId);
}
