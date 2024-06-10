package org.example.google;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> createUser(oAuth2User));

        return new CustomOAuth2User(oAuth2User, user.getRole());
    }

    private User createUser(OAuth2User oAuth2User) {
        User newUser = new User();
        newUser.setName(oAuth2User.getAttribute("name"));
        newUser.setEmail(oAuth2User.getAttribute("email"));
        newUser.setRole(UserRole.ADMIN);
        return userRepository.save(newUser);
    }
}
