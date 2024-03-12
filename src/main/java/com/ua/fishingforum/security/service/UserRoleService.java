package com.ua.fishingforum.security.service;


import com.ua.fishingforum.security.model.UserRole;

import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> findUserRole();
}
