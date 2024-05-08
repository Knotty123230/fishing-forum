package com.ua.fishingforum.user.chat.model;

import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "chats", schema = "forum")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinTable(schema = "forum", name = "chats_user-profiles",
            joinColumns = @JoinColumn
                    (
                            name = "chat_id", referencedColumnName = "id"
                    ),
            inverseJoinColumns = @JoinColumn
                    (
                            name = "user_profile_id", referencedColumnName = "id", unique = true
                    )
    )
    private List<UserProfile> members = new LinkedList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(schema = "forum", name = "chats_messages", joinColumns = @JoinColumn
            (
                    name = "chat_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn
                    (
                            name = "message_id", referencedColumnName = "id", unique = true
                    ))
    @ToString.Exclude
    private List<Message> messages = new LinkedList<>();
    @CreatedDate
    private Instant createdAt;

    public Set<UserProfile> getMembersHashSet() {
        return new HashSet<>(members);
    }
}
