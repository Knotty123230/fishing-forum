package com.ua.fishingforum.security.service;


import com.ua.fishingforum.security.model.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    void createUserAccount(UserAccount userAccount);

    Optional<UserAccount> findUserByUsername(String username);
}
