package com.ua.fishingforum.security.service.impl;

import com.ua.fishingforum.security.service.UserAccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.userAccountService.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("bad credentials"));
    }
}
