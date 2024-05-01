package com.ua.fishingforum.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class CommonController {

    @GetMapping("/")
    public ResponseEntity<String> getName(@AuthenticationPrincipal OidcUser oidcUser) {
        return ResponseEntity.ok(oidcUser.getGivenName());

    }
}
