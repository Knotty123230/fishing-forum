package com.ua.fishingforum.security.mapper.impl;


import com.ua.fishingforum.common.exception.CustomException;
import com.ua.fishingforum.security.mapper.RegisterRequestToUserAccountMapper;
import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.model.UserRole;
import com.ua.fishingforum.security.service.UserRoleService;
import com.ua.fishingforum.security.web.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RegisterRequestToUserAccountMapperImpl implements RegisterRequestToUserAccountMapper {
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount map(RegisterRequest registerRequest) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(registerRequest.username().toLowerCase(Locale.ROOT));
        userAccount.setPassword(this.passwordEncoder.encode(registerRequest.password()));
        userAccount.setFirstName(registerRequest.firstName());
        userAccount.setLastName(registerRequest.lastName());
        userAccount.setPhoneNumber(registerRequest.phoneNumber());
        UserRole roleUserNotFound = this.userRoleService.findUserRole().orElseThrow(() ->
                new CustomException("Role user not found"));
        userAccount.setUserRoles(Set.of(roleUserNotFound));
        return userAccount;
    }
}
