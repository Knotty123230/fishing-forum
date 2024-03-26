package com.ua.fishingforum.user.posts.image.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.fishingforum.user.posts.model.Post;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(schema = "forum", name = "post_images")
@Data
@EntityListeners(value = AuditingEntityListener.class)
public class Photo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    @CreatedDate
    private Instant createdAt;

    public Photo(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Photo() {
    }
}
