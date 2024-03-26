package com.ua.fishingforum.security.api.model;

import java.io.Serializable;

public record CurrentUserApiModel(Long userAccountId) implements Serializable {
}
