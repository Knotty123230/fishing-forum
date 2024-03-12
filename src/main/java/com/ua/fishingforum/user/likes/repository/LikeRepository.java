package com.ua.fishingforum.user.likes.repository;

import com.ua.fishingforum.user.likes.model.Like;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @EntityGraph(attributePaths = "userProfile")
    List<Like> findAllByPostId(Long postId);

    @EntityGraph(attributePaths = {"comment", "userProfile"})
    List<Like> findAllByCommentId(Long commentId);

    @EntityGraph(attributePaths = {"post", "userProfile"})
    Optional<Like> findByPostIdAndUserProfileId(Long postId, Long userProfileId);

    @EntityGraph(attributePaths = {"comment", "userProfile"})
    Optional<Like> findByUserProfileIdAndCommentId(Long userProfileId, Long commentId);
}
