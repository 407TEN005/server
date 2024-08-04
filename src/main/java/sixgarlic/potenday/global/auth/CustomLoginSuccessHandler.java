package sixgarlic.potenday.global.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sixgarlic.potenday.global.util.CookieUtil;
import sixgarlic.potenday.global.util.JwtUtil;
import sixgarlic.potenday.redis.service.RedisService;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetail = (CustomOAuth2User) authentication.getPrincipal();

        String kakaoId = customUserDetail.getKakaoId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority grantedAuthority = iterator.next();
        String authority = grantedAuthority.getAuthority();

        String accessToken = jwtUtil.createJwt("access", kakaoId, authority, Duration.ofHours(24).toMillis());
        String refreshToken = jwtUtil.createJwt("refresh", kakaoId, authority, Duration.ofDays(1).toMillis());

        redisService.set(kakaoId, refreshToken, Duration.ofDays(14));

        response.addCookie(cookieUtil.createCookie("refreshToken", refreshToken, (int) Duration.ofDays(14).toSeconds()));
        response.setStatus(HttpServletResponse.SC_OK);

        response.sendRedirect("https://tenten.potenday-sixgarlic.site/oauth2/redirect?accessToken=" + accessToken);
    }
}
