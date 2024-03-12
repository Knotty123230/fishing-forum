package com.ua.fishingforum.user.likes.service.impl;

import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.likes.repository.LikeRepository;
import com.ua.fishingforum.user.likes.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    @Override
    @Transactional
    public Like addLike(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public List<Like> findAllPostLikes(Long postId) {
        return this.likeRepository.findAllByPostId(postId);
    }

    @Override
    public List<Like> findAllCommentLikes(Long commentId) {
        return likeRepository.findAllByCommentId(commentId);
    }

    @Override
    public Optional<Like> findLikeByPostIdAndUserProfileId(Long postId, Long id) {
        return this.likeRepository.findByPostIdAndUserProfileId(postId, id);
    }

    @Override
    @Transactional
    public void deleteLike(Like like) {
        this.likeRepository.delete(like);
    }

    @Override
    public Optional<Like> findLikeByUserProfileIdAndCommentId(Long userProfileId, Long commentId) {
        return this.likeRepository.findByUserProfileIdAndCommentId(userProfileId, commentId);
    }


}
