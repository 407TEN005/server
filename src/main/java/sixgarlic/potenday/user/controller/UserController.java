package sixgarlic.potenday.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sixgarlic.potenday.global.auth.CustomOAuth2User;
import sixgarlic.potenday.user.dto.UserResponse;
import sixgarlic.potenday.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/current")
    public ResponseEntity findCurrentUser(@AuthenticationPrincipal CustomOAuth2User oAuth2User) {

        String kakaoId = oAuth2User.getKakaoId();

        UserResponse userResponse = userService.findCurrentUser(kakaoId);

        return ResponseEntity.ok(userResponse);
    }
}
