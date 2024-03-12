package com.ua.fishingforum.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "users", name = "user_accounts")
@Getter
@Setter
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(
            schema = "users",
            name = "user_accounts_roles",
            joinColumns = {
                    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
            }
    )
    @Getter
    @Setter
    private Set<UserRole> userRoles = new HashSet<>();

}