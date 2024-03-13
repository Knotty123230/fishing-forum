package com.ua.fishingforum.user.likes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(schema = "forum", name = "likes")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinTable(name = "user_profile_likes",
            joinColumns = @JoinColumn(name = "like_id"),
            inverseJoinColumns = @JoinColumn(name = "user_profile_id"), schema = "forum")
    @JsonIgnore
    private UserProfile userProfile;
    @ManyToOne
    @JoinTable(name = "post_likes",
            joinColumns = @JoinColumn(name = "like_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"), schema = "forum")
    @JsonIgnore
    @ToString.Exclude
    private Post post;
    @ManyToOne
    @JoinTable(name = "comment_likes",
            joinColumns = @JoinColumn(name = "like_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"), schema = "forum")
    @JsonIgnore
    @ToString.Exclude
    private Comment comment;
    @CreatedDate
    private Instant createdTimestamp;
}
