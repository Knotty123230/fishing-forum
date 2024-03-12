package com.ua.fishingforum.user.comments.repository;

import com.ua.fishingforum.user.comments.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = "userProfile")
    Optional<Comment> findByUserProfileIdAndId(Long userProfileId, Long commentId);

    @EntityGraph(attributePaths = "userProfile")
    Optional<List<Comment>> findByPostIdOrderByLikes(Long postId);

    @EntityGraph(attributePaths = "userProfile")
    List<Comment> findByPostIdOrderByCreatedTimestamp(Long postId);
}
