package com.ua.fishingforum.marks.model;

import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "marks", schema = "geo")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double lat;
    private double lng;
    @OneToMany
    private List<Post> posts;
    @ManyToOne
    private UserProfile userProfile;
}
