package com.ua.fishingforum.user.posts.image.repository;

import com.ua.fishingforum.user.posts.image.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
