package sixgarlic.potenday.global.filter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import sixgarlic.potenday.global.auth.CustomOAuth2User;
import sixgarlic.potenday.global.util.JwtUtil;
import sixgarlic.potenday.redis.service.RedisService;
import sixgarlic.potenday.user.model.User;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RedisService redisService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader("Authorization");

        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String originToken = accessToken.substring(7);

        try {
            if (jwtUtil.isExpired(originToken)) {
                PrintWriter writer = response.getWriter();
                writer.println("access token이 만료되었습니다.");

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (ExpiredJwtException e) {
            PrintWriter writer = response.getWriter();
            writer.println("access token이 만료되었습니다.");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = jwtUtil.getCategory(originToken);

        if (!"access".equals(category)) {
            PrintWriter writer = response.getWriter();
            writer.println("access token이 아닌 refresh token이 헤더값에 보내졌습니다.");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (redisService.exists(originToken)) {
            throw new AuthorizationServiceException("비정상적인 접근입니다.");
        }

        String kakaoId = jwtUtil.getKakaoId(originToken);
        String authority = jwtUtil.getAuthority(originToken);

        User user = User.builder()
                .kakaoId(kakaoId)
                .authority(authority)
                .build();

        CustomOAuth2User customOAuth2User = new CustomOAuth2User(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
