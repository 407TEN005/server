package sixgarlic.potenday.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sixgarlic.potenday.global.auth.CustomOAuth2User;
import sixgarlic.potenday.global.auth.KakaoResponse;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.model.UserStatus;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        String accessToken = userRequest.getAccessToken().getTokenValue();

        KakaoResponse kakaoResponse = new KakaoResponse(oAuth2User.getAttributes());

        String kakaoId = kakaoResponse.getProviderId();

        Optional<User> optionalUser = userRepository.findByKakaoId(kakaoId);

        if (optionalUser.isEmpty()) {
            User user = User.builder()
                    .kakaoId(kakaoId)
                    .nickname(kakaoResponse.getName())
                    .authority("ROLE_USER")
                    .status(UserStatus.NEW)
                    .build();

            userRepository.save(user);

            return new CustomOAuth2User(user);
        } else {
            User user = optionalUser.get();
            return new CustomOAuth2User(user);
        }


    }
}
