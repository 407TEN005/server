package sixgarlic.potenday.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sixgarlic.potenday.test.dto.TestDeriveRequest;
import sixgarlic.potenday.test.model.Answer;
import sixgarlic.potenday.test.model.FamilyRole;
import sixgarlic.potenday.test.model.Test;
import sixgarlic.potenday.test.repository.TestRepository;
import sixgarlic.potenday.traveltype.model.TravelType;
import sixgarlic.potenday.traveltype.service.UserTypeService;
import sixgarlic.potenday.user.model.User;
import sixgarlic.potenday.user.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TestService {

    private final UserTypeService userTypeService;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    public TravelType submitTestResults(Long userId, TestDeriveRequest testDeriveRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        FamilyRole familyRole = testDeriveRequest.getFamilyRole();

        for (Answer answer : testDeriveRequest.getAnswers()) {
            Test test = Test.builder()
                    .answer(answer)
                    .familyRole(familyRole)
                    .user(user)
                    .build();

            testRepository.save(test);
        }

        return userTypeService.deriveTravelType(userId, testDeriveRequest);
    }

    public TravelType submitTestResults(TestDeriveRequest testDeriveRequest) {
        return userTypeService.deriveTravelType(testDeriveRequest);
    }
}
