package com.ua.fishingforum.user.profile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(schema = "forum", name = "user_profiles")
public class UserProfile {
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Column(nullable = false)
    private String imageLink;

    public UserProfile(String nickname, String imageLink) {
        this.nickname = nickname;
        this.imageLink = imageLink;
    }

    public UserProfile(Long id, String nickname, String imageLink) {
        this.id = id;
        this.nickname = nickname;
        this.imageLink = imageLink;
    }

    public UserProfile() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(nickname, that.nickname) && Objects.equals(imageLink, that.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, imageLink);
    }
}
