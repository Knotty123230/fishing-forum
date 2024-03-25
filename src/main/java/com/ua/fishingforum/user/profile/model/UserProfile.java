package com.ua.fishingforum.user.profile.model;

import com.ua.fishingforum.user.posts.image.model.Photo;
import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "image_id")
    private Photo imageLink;

    public UserProfile(String nickname, Long id) {
        this.id = id;
        this.nickname = nickname;
    }

    public UserProfile(Long id, String nickname, Photo photo) {
        this.id = id;
        this.nickname = nickname;
        this.imageLink = photo;
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
