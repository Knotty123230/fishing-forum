package com.ua.fishingforum.user.comments.service.impl;

import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.comments.repository.CommentRepository;
import com.ua.fishingforum.user.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new CustomException("коментарій з таким айді: %s не знайдено".formatted(commentId)));
    }

    @Override
    public Optional<Comment> findCommentByUserProfileIdAndCommentId(Long id, Long source) {
        return this.commentRepository.findByUserProfileIdAndId(id, source);
    }

    @Override
    public List<Comment> findAllCommentsByPostIdAndOrderByDate(Long postId) {
        return this.commentRepository.findByPostIdOrderByCreatedTimestamp(postId);
    }

    @Override
    public Optional<List<Comment>> findAllCommentsByPostIdAndOrderByLikes(Long postId) {
        return this.commentRepository.findByPostIdOrderByLikes(postId);
    }
}
