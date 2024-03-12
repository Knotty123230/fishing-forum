package com.ua.fishingforum.security.mapper;


import com.ua.fishingforum.common.mapper.Mapper;
import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.web.dto.RegisterRequest;

public interface RegisterRequestToUserAccountMapper extends Mapper<UserAccount, RegisterRequest> {
}
