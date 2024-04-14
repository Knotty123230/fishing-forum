package com.ua.fishingforum.security.service.impl;

import com.ua.fishingforum.security.model.UserAccount;
import com.ua.fishingforum.security.model.UserRole;
import com.ua.fishingforum.security.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class Oauth2Service implements OAuth2AuthorizedClientService {
    private final UserAccountService userAccountService;

    @NotNull
    private static UserAccount getUserAccount(Result result) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(result.email());
        userAccount.setFirstName(result.givenName());
        UserRole userRole = new UserRole();
        userRole.setAuthority("ROLE_USER");
        userAccount.setUserRoles(Set.of(userRole));
        return userAccount;
    }

    @NotNull
    private static Result extractToken(Authentication principal) {
        DefaultOidcUser principal1 = (DefaultOidcUser) principal.getPrincipal();
        OidcIdToken idToken = principal1.getIdToken();
        String email = idToken.getEmail();
        String givenName = idToken.getGivenName();
        String picture = idToken.getPicture();
        return new Result(idToken, email, givenName, picture);
    }

    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
        Result result = extractToken(principal);
        boolean present = userAccountService.findUserByUsername(result.email()).isPresent();
        if (present) {
            return;
        }
        UserAccount userAccount = getUserAccount(result);
        userAccountService.createUserAccount(userAccount);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }

    private record Result(OidcIdToken idToken, String email, String givenName, String image) {
    }
}
