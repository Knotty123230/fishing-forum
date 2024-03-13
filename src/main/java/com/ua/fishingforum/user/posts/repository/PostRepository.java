package com.ua.fishingforum.user.posts.repository;

import com.ua.fishingforum.user.posts.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p JOIN FETCH p.userProfile LEFT JOIN FETCH p.likes l ")
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllPostsByUserProfileId(Long id, PageRequest pageRequest);

    @Query("SELECT p FROM Post p JOIN FETCH p.userProfile JOIN fetch p.likes WHERE p.userProfile.id != :id")
    Page<Post> findNews(@Param("id") Long id, Pageable pageRequest);
}
