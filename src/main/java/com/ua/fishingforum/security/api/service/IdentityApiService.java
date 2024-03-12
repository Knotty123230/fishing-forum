package com.ua.fishingforum.security.api.service;


import com.ua.fishingforum.security.api.model.CurrentUserApiModel;

import java.util.Optional;

public interface IdentityApiService {
    Optional<CurrentUserApiModel> currentUserAccount();
}
