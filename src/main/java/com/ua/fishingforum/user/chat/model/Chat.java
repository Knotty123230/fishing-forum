package com.ua.fishingforum.user.chat.model;

import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

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
    @JoinTable(name = "chats_user-profiles",
            joinColumns = @JoinColumn
                    (
                            name = "chat_id", referencedColumnName = "id"
                    ),
            inverseJoinColumns = @JoinColumn
                    (
                            name = "user_profile_id", referencedColumnName = "id", unique = true
                    )
    )
    private List<UserProfile> members;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chats_messages", joinColumns = @JoinColumn
            (
                    name = "chat_id", referencedColumnName = "id"
            ),
    inverseJoinColumns = @JoinColumn
            (
                    name = "message_id", referencedColumnName = "id", unique = true
            ))
    private List<Message> messages;
    @CreatedDate
    private Instant createdAt;
}
