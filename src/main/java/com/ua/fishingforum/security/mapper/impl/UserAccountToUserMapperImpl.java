package com.ua.fishingforum.security.mapper.impl;

import com.ua.fishingforum.security.mapper.UserAccountToUserMapper;
import com.ua.fishingforum.security.model.UserAccount;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserAccountToUserMapperImpl implements UserAccountToUserMapper {
    @Override
    public User map(UserAccount userAccount) {
        return new User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getUserRoles());
    }
}
