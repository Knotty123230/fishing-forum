package com.ua.fishingforum.security.service.impl;

import com.ua.fishingforum.security.model.UserRole;
import com.ua.fishingforum.security.repository.UserRoleRepository;
import com.ua.fishingforum.security.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    @Override
    public Optional<UserRole> findUserRole() {
        return userRoleRepository.findByAuthority("ROLE_USER");
    }
}
