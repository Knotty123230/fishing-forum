package com.ua.fishingforum.user.chat.model;

import com.ua.fishingforum.user.profile.model.UserProfile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "chats", schema = "forum")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = AuditingEntityListener.class)
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany( fetch = FetchType.EAGER)
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
    @OneToMany(fetch = FetchType.EAGER)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) && Objects.equals(name, chat.name) && Objects.equals(members, chat.members) && Objects.equals(messages, chat.messages) && Objects.equals(createdAt, chat.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, members, messages, createdAt);
    }
}
