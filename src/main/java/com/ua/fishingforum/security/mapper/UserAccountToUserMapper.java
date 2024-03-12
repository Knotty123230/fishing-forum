package com.ua.fishingforum.security.mapper;

import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.security.model.UserAccount;
import org.springframework.security.core.userdetails.User;

public interface UserAccountToUserMapper extends Mapper<User, UserAccount> {
}
