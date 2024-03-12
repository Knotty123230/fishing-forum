package com.ua.fishingforum.user.profile.repository;

import com.ua.fishingforum.user.profile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByNickname(String nickname);
}
