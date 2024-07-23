package sixgarlic.potenday.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sixgarlic.potenday.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


}
