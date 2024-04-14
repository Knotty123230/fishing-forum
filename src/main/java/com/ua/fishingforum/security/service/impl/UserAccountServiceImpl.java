package com.ua.fishingforum.security.service.impl;


import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.repository.UserAccountRepository;
import com.ua.fishingforum.security.service.UserAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    @Transactional
    @CacheEvict(value = "user-accounts", allEntries = true)
    public void createUserAccount(UserAccount userAccount) {
        boolean existsByUsername = this.userAccountRepository.existsByUsername(userAccount.getUsername());
        if (existsByUsername) {
            throw new CustomException("Account with this username already exists");
        }
        userAccountRepository.save(userAccount);
    }

    @Override
    @Transactional
    @Cacheable(value = "user-accounts", key = "#username")
    public Optional<UserAccount> findUserByUsername(String username) {
        return this.userAccountRepository.findByUsername(username);
    }
}
