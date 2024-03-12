package com.ua.fishingforum.user.comments.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;


@Entity
@Data
@Table(schema = "forum", name = "comments")
@EntityListeners(value = AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_profile_id")
    @JsonIgnore
    @ToString.Exclude
    private UserProfile userProfile;
    @ManyToOne
    @JoinTable(schema = "forum", name = "user_posts_comments",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    @JsonIgnore
    @ToString.Exclude
    private Post post;
    @OneToMany
    @JoinTable(name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "like_id"), schema = "forum")
    private List<Like> likes = new LinkedList<>();
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdTimestamp;
    @LastModifiedDate
    private Instant editedTimestamp;
}
