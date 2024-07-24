package sixgarlic.potenday.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sixgarlic.potenday.test.dto.TestRequestDto;
import sixgarlic.potenday.test.model.Answer;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.test.repository.TestRepository;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public void saveTestResults(Long userId, TestRequestDto testRequestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException());

        FamilyRole familyRole = testRequestDto.getFamilyRole();

        List<Long> results = new ArrayList<>();

        for (Answer answer : testRequestDto.getAnswers()) {
            Test test = Test.builder()
                    .answer(answer)
                    .familyRole(familyRole)
                    .user(user)
                    .build();

            testRepository.save(test);
        }
    }
}
