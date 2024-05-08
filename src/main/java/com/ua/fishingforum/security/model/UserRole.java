package com.ua.fishingforum.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "users", name = "user_roles")
@Getter
@Setter
public class UserRole implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String authority;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = "users",
            name = "user_accounts_roles",
            joinColumns = {
                    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
            }
    )
    private Set<UserAccount> userAccount;

    @Override
    public String getAuthority() {
        return authority;
    }


}
