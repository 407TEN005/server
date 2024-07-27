package sixgarlic.potenday.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sixgarlic.potenday.user.dto.UserResponse;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse findCurrentUser(String kakaoId) {

        User user = userRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new NoSuchElementException("찾으시는 유저의 정보가 없습니다."));

        return UserResponse.from(user);
    }
}
