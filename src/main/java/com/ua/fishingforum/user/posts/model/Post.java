package com.ua.fishingforum.user.posts.model;

import com.ua.fishingforum.marks.model.Mark;
import com.ua.fishingforum.user.comments.model.Comment;
import com.ua.fishingforum.user.likes.model.Like;
import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Table(schema = "forum", name = "user_posts")
@EntityListeners(value = AuditingEntityListener.class)
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "post_images_links", schema = "forum", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<Photo> photos;
    @LastModifiedDate
    private Instant modifiedTimestamp;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Instant createdTimestamp;
    @ManyToOne
    @ToString.Exclude
    private UserProfile userProfile;
    @ManyToOne
    @ToString.Exclude
    private Mark mark;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "like_id"), schema = "forum")
    private List<Like> likes;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "forum", name = "user_posts_comments",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments;

    public Post(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Post(String name, String description, List<Photo> photos) {
        this.name = name;
        this.description = description;
        this.photos = photos;
    }

    public Post() {
    }
}
