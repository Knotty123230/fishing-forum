package com.ua.fishingforum.security.service.impl;


import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.repository.UserAccountRepository;
import com.ua.fishingforum.security.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public void createUserAccount(UserAccount userAccount) {
        boolean existsByUsername = this.userAccountRepository.existsByUsername(userAccount.getUsername());
        if (existsByUsername) {
            throw new RuntimeException("Account with this username already exists");
        }

        userAccountRepository.save(userAccount);
    }

    @Override
    public Optional<UserAccount> findUserByUsername(String username) {
        return this.userAccountRepository.findByUsername(username);
    }
}
