package com.MyFirstApp.Haribo.config.auth;

import com.MyFirstApp.Haribo.config.auth.dto.OAuthAttributes;
import com.MyFirstApp.Haribo.config.auth.dto.SessionUser;
import com.MyFirstApp.Haribo.domain.User.AppUser;
import com.MyFirstApp.Haribo.domain.User.AppUserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AppUserRepository appUserRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        AppUser appUser = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(appUser));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(appUser.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private AppUser saveOrUpdate(OAuthAttributes attributes) {
        AppUser appUser = appUserRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return appUserRepository.save(appUser);
    }
}